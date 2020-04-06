package bot.web.controller;

import bot.domain.dto.CityPlaceDto;
import bot.domain.dto.create.CityCreateDto;
import bot.domain.dto.create.CityPlaceCreateDto;
import bot.domain.dto.out.CityCreatedDto;
import bot.service.api.ICityOperationService;
import bot.web.response.details.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityOperationService cityOperationService;

    @PostMapping(value = "/add")
    private ResponseEntity<CityCreatedDto> createNewOne(@RequestBody @Valid CityCreateDto ccd) {
        return ResponseEntity.ok(cityOperationService.create(ccd));
    }

    @PostMapping(value = "{id}/place/add")
    private ResponseEntity<CityPlaceDto> addPlaceToExistingCity(@PathVariable @Min(1L) Long id,
                                                                  @RequestBody @Valid CityPlaceCreateDto cpd) {
        return ResponseEntity.ok(cityOperationService.addPlace(id, cpd));
    }

    @PutMapping(value = "{id}/edit")
    private ResponseEntity<CityCreatedDto> editExistingCity(@PathVariable @Min(1L) Long id,
                                                            @RequestBody @Valid CityCreateDto ccd) {
        return ResponseEntity.ok(cityOperationService.editCity(id, ccd));
    }

    @DeleteMapping(value = "{id}/delete")
    private ResponseEntity<ResponseDetails> deleteExistingCity(@PathVariable @Min(1L) Long id) {
        cityOperationService.deleteCity(id);
        return ResponseEntity.ok(ResponseDetails.success());
    }

    @DeleteMapping(value = "{cityId}/place/{placeId}/delete")
    private ResponseEntity<ResponseDetails> deleteExistingCityPlace(@PathVariable @Min(1L) Long cityId,
                                                                    @PathVariable @Min(1L) Long placeId) {
        cityOperationService.deleteCityPlace(cityId, placeId);
        return ResponseEntity.ok(ResponseDetails.success());
    }

    @PutMapping(value = "{cityId}/place/{placeId}/edit")
    private ResponseEntity<CityPlaceDto> editExistingCityPlace(@PathVariable @Min(1L) Long cityId,
                                                               @PathVariable @Min(1L) Long placeId,
                                                               @RequestBody CityPlaceCreateDto dto) {
        return ResponseEntity.ok(cityOperationService.editCityPlace(cityId, placeId, dto));
    }

}
