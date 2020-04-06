package bot.persistence;

import bot.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public interface ICityRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> findByNameAndCountryIgnoreCase(String name, String country);

    List<CityEntity> findAllByNameIgnoreCase(String name);
}
