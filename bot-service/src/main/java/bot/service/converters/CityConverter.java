package bot.service.converters;

import bot.domain.dto.CityPlaceDto;
import bot.domain.dto.create.CityCreateDto;
import bot.domain.dto.create.CityPlaceCreateDto;
import bot.domain.dto.out.CityCreatedDto;
import bot.domain.entity.CityEntity;
import bot.domain.entity.CityParamsEntity;
import bot.domain.entity.CityPlaceEntity;
import bot.service.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Component
public class CityConverter {

    public CityCreatedDto map(CityEntity cityEntity) {
        if (cityEntity == null) return null;
        CityParamsEntity cityParams = cityEntity.getCityParams();
        return
                CityCreatedDto.builder()
                        .country(cityEntity.getCountry())
                        .id(cityEntity.getId())
                        .name(cityEntity.getName())
                        .places(CollectionUtils.map(cityEntity.getPlaces(), this::map))
                        .population(cityParams.getPopulation())
                        .square(cityParams.getSquare())
                        .image(cityEntity.getImage())
                        .build();

    }

    public CityEntity map(CityCreatedDto ccd) {
        if (ccd == null) return null;

        CityEntity city = new CityEntity();
        city.setCountry(ccd.getCountry());
        city.setName(ccd.getName());
        city.setPlaces(CollectionUtils.map(ccd.getPlaces(), this::map, city));
        city.setImage(ccd.getImage());

        CityParamsEntity cityParams = new CityParamsEntity(city, ccd.getSquare(), ccd.getPopulation());
        city.setCityParams(cityParams);
        return city;
    }

    private CityPlaceEntity map(CityPlaceDto dto, CityEntity location) {
        if (dto == null) return null;
        return new CityPlaceEntity(dto.getPlace().getSignature(), dto.getPlace().getDescription(), location);
    }

    public CityCreatedDto map(CityCreateDto ccd) {
        if (ccd == null) return null;
        return CityCreatedDto.builder()
                .population(ccd.getPopulation())
                .square(ccd.getSquare())
                .country(ccd.getCountry())
                .name(ccd.getName())
                .places(new ArrayList<>())
                .image(ccd.getImage())
                .build();
    }

    public CityPlaceEntity map(CityPlaceCreateDto cpd, CityEntity location) {
        if (cpd == null) return null;
        return new CityPlaceEntity(cpd.getSignature(), cpd.getDescription(), location);
    }

    public CityPlaceDto map(CityPlaceEntity cpe) {
        if (cpe == null) return null;
        CityPlaceCreateDto place = new CityPlaceCreateDto(cpe.getDescription(), cpe.getPlaceName());
        return CityPlaceDto.builder()
                .place(place)
                .id(cpe.getId())
                .build();
    }
}
