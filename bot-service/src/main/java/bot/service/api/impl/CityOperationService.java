package bot.service.api.impl;

import bot.domain.dto.CityPlaceDto;
import bot.domain.dto.create.CityCreateDto;
import bot.domain.dto.create.CityPlaceCreateDto;
import bot.domain.dto.out.CityCreatedDto;
import bot.domain.entity.CityEntity;
import bot.domain.entity.CityParamsEntity;
import bot.domain.entity.CityPlaceEntity;
import bot.persistence.ICityPlaceRepository;
import bot.persistence.ICityRepository;
import bot.service.api.ICityOperationService;
import bot.service.converters.CityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.Optional;

import static bot.service.validation.ExceptionStringTemplates.*;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Service
@Transactional
public class CityOperationService implements ICityOperationService {

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private ICityPlaceRepository cityPlaceRepository;

    @Autowired
    private CityConverter cityConverter;

    @Override
    public CityCreatedDto create(CityCreateDto ccd) {
        Optional<CityEntity> existing = cityRepository.findByNameAndCountryIgnoreCase(ccd.getName(), ccd.getCountry());
        if (existing.isPresent()) {
            throw new ValidationException(CITY_EXIST);
        }
        CityCreatedDto cityCreatedDto = cityConverter.map(ccd);
        CityEntity city = cityRepository.save(cityConverter.map(cityCreatedDto));
        cityCreatedDto.setId(city.getId());
        return cityCreatedDto;
    }

    @Override
    public CityPlaceDto addPlace(Long cityId, CityPlaceCreateDto cpd) {
        CityEntity existing = cityRepository.findById(cityId).orElseThrow(() -> new ValidationException(CITY_NOT_FOUND));
        CityPlaceEntity placeEntity = cityConverter.map(cpd, existing);
        cityPlaceRepository.save(placeEntity);
        return cityConverter.map(placeEntity);
    }

    @Override
    public CityCreatedDto editCity(Long cityId, CityCreateDto ccd) {
        CityEntity existing = cityRepository.findById(cityId).orElseThrow(() -> new ValidationException(CITY_NOT_FOUND));
        existing.setImage(ccd.getImage());
        existing.setCountry(ccd.getCountry());
        existing.setName(ccd.getName());

        CityParamsEntity cityParams = existing.getCityParams();
        cityParams.setPopulation(ccd.getPopulation());
        cityParams.setSquare(ccd.getSquare());

        return cityConverter.map(existing);
    }

    @Override
    public void deleteCity(Long cityId) {
        CityEntity existing = cityRepository.findById(cityId).orElseThrow(() -> new ValidationException(CITY_NOT_FOUND));
        cityRepository.delete(existing);
    }

    @Override
    public void deleteCityPlace(Long cityId, Long placeId) {
        cityRepository.findById(cityId).orElseThrow(() -> new ValidationException(CITY_NOT_FOUND));
        CityPlaceEntity existingPlace = cityPlaceRepository.findByIdAndLocationId(placeId, cityId)
                .orElseThrow(() -> new ValidationException(CITY_PLACE_NOT_FOUND));
        cityPlaceRepository.delete(existingPlace);
    }

    @Override
    public CityPlaceDto editCityPlace(Long cityId, Long placeId, CityPlaceCreateDto dto) {
        cityRepository.findById(cityId).orElseThrow(() -> new ValidationException(CITY_NOT_FOUND));
        CityPlaceEntity existingPlace = cityPlaceRepository.findByIdAndLocationId(placeId, cityId)
                .orElseThrow(() -> new ValidationException(CITY_PLACE_NOT_FOUND));
        existingPlace.setDescription(dto.getDescription());
        existingPlace.setPlaceName(dto.getSignature());
        return cityConverter.map(existingPlace);
    }
}
