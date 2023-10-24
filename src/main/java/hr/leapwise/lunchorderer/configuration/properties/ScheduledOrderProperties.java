package hr.leapwise.lunchorderer.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "hr.leapwise.lunchorderer.order")
public class ScheduledOrderProperties {

    private List<Long> mealIds;
    private String email;
    private String cron;
}
