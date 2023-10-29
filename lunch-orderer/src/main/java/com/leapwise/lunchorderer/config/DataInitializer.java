package com.leapwise.lunchorderer.config;

import com.leapwise.lunchorderer.models.domain.Meal;
import com.leapwise.lunchorderer.repository.MealRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    private final MealRepository mealRepository;

    public DataInitializer(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            
            Meal meal1 = new Meal();
            meal1.setName("Varivo od mix mahunarki");
            meal1.setPrice(3.60);

            Meal meal2 = new Meal();
            meal2.setName("Pohani file oslića – krumpir salata s rikulom");
            meal2.setPrice(6.30);

            Meal meal3 = new Meal();
            meal3.setName("Pohani file oslića, umak od vlasca i krastavca - slani krumpir");
            meal3.setPrice(6.30);

            Meal meal4 = new Meal();
            meal4.setName("Steak tune sa žara, tršćanski umak – blitva s krumpirom");
            meal4.setPrice(10.00);

            Meal meal5 = new Meal();
            meal5.setName("Orada sa žara, tršćanski umak – blitva s krumpirom");
            meal5.setPrice(7.10);

            Meal meal6 = new Meal();
            meal6.setName("Crni rižoto od liganja s parmezanom");
            meal6.setPrice(6.50);

            Meal meal7 = new Meal();
            meal7.setName("Pureći medaljoni u umaku od pesta s tjesteninom");
            meal7.setPrice(6.00);

            Meal meal8 = new Meal();
            meal8.setName("Juha od rajčice");
            meal8.setPrice(1.20);

            Meal meal9 = new Meal();
            meal9.setName("Salata miješana");
            meal9.setPrice(1.10);

            mealRepository.saveAll(List.of(meal1, meal2, meal3, meal4, meal5, meal6, meal7, meal8, meal9));
        };
    }

}
