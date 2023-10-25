package hr.leapwise.lunchorderer.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "hr.leapwise.lunchorderer.order")
public class OrderSchedulerProperties {

    private String email;
    private String cron;
}
