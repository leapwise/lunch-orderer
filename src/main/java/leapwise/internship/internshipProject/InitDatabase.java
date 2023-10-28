package leapwise.internship.internshipProject;

import leapwise.internship.internshipProject.domain.MenuItem;
import leapwise.internship.internshipProject.repository.MenuRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * Spring configuration which initializes the virtual H2 database
 *
 * @author Vito
 */
@Configuration
public class InitDatabase {

    /**
     * Allows access to the menu repository
     */
    private final MenuRepository menuRepository;

    /**
     * Simple constructor which assigns the provided menu repository
     *
     * @param menuRepository The provided menu repository
     */
    public InitDatabase(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * Method which is called when the {@link ApplicationReadyEvent} is invoked.
     * Initializes the database with the initial values
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        menuRepository.save(new MenuItem("Varivo od mix mahunarki", 3.60));
        menuRepository.save(new MenuItem("Pohani file oslića – krumpir salata s rikulom", 6.30));
        menuRepository.save(new MenuItem("Pohani file oslića, umak od vlasca i krastavca - slani krumpir", 6.30));
        menuRepository.save(new MenuItem("Steak tune sa žara, tršćanski umak – blitva s krumpirom", 10));
        menuRepository.save(new MenuItem("Orada sa žara, tršćanski umak – blitva s krumpirom", 7.10));
        menuRepository.save(new MenuItem("Crni rižoto od liganja s parmezanom", 6.50));
        menuRepository.save(new MenuItem("Pureći medaljoni u umaku od pesta s tjesteninom", 6));
        menuRepository.save(new MenuItem("Juha od rajčice", 1.20));
        menuRepository.save(new MenuItem("Salata miješana", 1.10));

    }
}
