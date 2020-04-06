package bot.persistence;

import bot.domain.entity.CityPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
public interface ICityPlaceRepository extends JpaRepository<CityPlaceEntity, Long> {
    Optional<CityPlaceEntity> findByIdAndLocationId(Long placeId, Long locationId);
}
