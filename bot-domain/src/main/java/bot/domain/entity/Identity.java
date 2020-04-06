package bot.domain.entity;

import lombok.Getter;

import javax.persistence.*;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Getter
@MappedSuperclass
public abstract class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
