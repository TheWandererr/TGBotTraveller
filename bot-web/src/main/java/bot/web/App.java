package bot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = {bot.persistence.ICityRepository.class})
@ComponentScan(basePackages = {"bot"})
@EntityScan(basePackages = {"bot"})
public class App {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(App.class, args);
    }
}
