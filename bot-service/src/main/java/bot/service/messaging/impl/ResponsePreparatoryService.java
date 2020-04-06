package bot.service.messaging.impl;

import bot.domain.content.Command;
import bot.domain.dto.CityPlaceDto;
import bot.domain.dto.create.CityPlaceCreateDto;
import bot.domain.dto.out.CityCreatedDto;
import bot.domain.entity.CityEntity;
import bot.domain.parsing.ParsedCityInfo;
import bot.domain.parsing.ParsedCommandInfo;
import bot.persistence.ICityRepository;
import bot.service.converters.CityConverter;
import bot.service.messaging.IResponseCreatingService;
import bot.service.messaging.IResponsePreparatoryService;
import bot.service.parser.CityParser;
import bot.service.utils.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static bot.service.messaging.MessageTemplates.*;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@Service
@Transactional
public class ResponsePreparatoryService implements IResponsePreparatoryService {

    private static final int SMALL = 1;
    private static final int MEDIUM = 2;

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private CityConverter cityConverter;

    @Autowired
    private CityParser cityParser;

    @Autowired
    private IResponseCreatingService responseCreatingService;

    @Override
    public SendMessage prepareResponseForCommand(ParsedCommandInfo parsedCommandInfo, Message message) {
        Command command = parsedCommandInfo.getParsedBody();
        switch (command) {
            case HELP: {
                return responseCreatingService.createSendMessageResponse(message, HELP_COMMAND_TEXT);
            }
            case START: {
                return responseCreatingService.createSendMessageResponse(message, START_COMMAND_TEXT);
            }
            default: {
                return responseCreatingService.createSendMessageResponse(message, INCORRECT_COMMAND_TEXT);
            }
        }
    }

    @Override
    public PartialBotApiMethod<Message> prepareResponseForTextMessage(Message message) {
        ParsedCityInfo cityInfo = cityParser.parse(message.getText());
        if (cityInfo.isParsingFailed()) {
            return responseCreatingService.createSendMessageResponse(message, cityInfo.getFailReason());
        } else {
            return prepareCityResponse(cityInfo, message);
        }
    }

    private PartialBotApiMethod<Message> prepareCityResponse(ParsedCityInfo cityInfo, Message message) {
        String city = cityInfo.getParsedBody();
        List<CityEntity> founded = cityRepository.findAllByNameIgnoreCase(city);

        if (isEmpty(founded)) {
            return responseCreatingService.createSendMessageResponse(message, CITY_NOT_FOUND, message.getFrom().getFirstName());
        }

        CityEntity random = founded.get(Randomizer.nextInt(founded.size()));
        CityCreatedDto target = cityConverter.map(random);
        List<CityPlaceDto> cityPlaces = target.getPlaces();

        if (isEmpty(cityPlaces)) {
            return prepareCityResponseWithoutPlaces(target, message);
        } else {
            return prepareCityResponseWithSomePlaces(cityPlaces, message);
        }
    }

    private SendPhoto prepareCityResponseWithoutPlaces(CityCreatedDto city, Message message) {
        return responseCreatingService.createSendPhotoResponse(message,
                city.getImage(),
                CITY_WITHOUT_PLACES_CAPTION,
                city.getPopulation().toString(),
                city.getSquare().toString()
        );
    }

    private SendMessage prepareCityResponseWithSomePlaces(List<CityPlaceDto> places, Message message) {
        int size = places.size();
        String messageTemplate;
        switch (size) {
            case SMALL: {
                CityPlaceDto target = places.get(0);
                messageTemplate = getRandomAnswerTemplate(cityShortAnswer);
                CityPlaceCreateDto place = target.getPlace();
                return responseCreatingService.createSendMessageResponse(message, messageTemplate, place.getSignature(), place.getDescription());
            }
            case MEDIUM: {
                messageTemplate = getRandomAnswerTemplate(cityMediumAnswer);
                CityPlaceDto first = places.get(0);
                CityPlaceDto second = places.get(1);
                return responseCreatingService.createSendMessageResponse(message,
                        messageTemplate,
                        first.getPlace().getSignature(),
                        first.getPlace().getDescription(),
                        second.getPlace().getSignature(),
                        second.getPlace().getDescription());
            }
            default: {
                messageTemplate = getRandomAnswerTemplate(cityLongAnswer);
                int index = Randomizer.nextInt(places.size());
                CityPlaceDto first = places.get(index);
                places.remove(index);
                index = Randomizer.nextInt(places.size());
                CityPlaceDto second = places.get(index);
                places.remove(index);
                index = Randomizer.nextInt(places.size());
                CityPlaceDto third = places.get(index);

                return responseCreatingService.createSendMessageResponse(message,
                        messageTemplate,
                        first.getPlace().getSignature(),
                        first.getPlace().getDescription(),
                        second.getPlace().getSignature(),
                        second.getPlace().getDescription(),
                        third.getPlace().getSignature(),
                        third.getPlace().getDescription());
            }
        }
    }

    private String getRandomAnswerTemplate(List<String> source) {
        return source.get(Randomizer.nextInt(source.size()));
    }
}
