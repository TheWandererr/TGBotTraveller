package bot.service.api;

import bot.domain.dto.CityPlaceDto;
import bot.domain.dto.create.CityCreateDto;
import bot.domain.dto.create.CityPlaceCreateDto;
import bot.domain.dto.out.CityCreatedDto;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public interface ICityOperationService {
    CityCreatedDto create(CityCreateDto ccd);

    CityPlaceDto addPlace(Long cityId, CityPlaceCreateDto cpd);

    CityCreatedDto editCity(Long cityId, CityCreateDto ccd);

    void deleteCity(Long cityId);

    void deleteCityPlace(Long cityId, Long placeId);

    CityPlaceDto editCityPlace(Long cityId, Long placeId, CityPlaceCreateDto dto);
}

