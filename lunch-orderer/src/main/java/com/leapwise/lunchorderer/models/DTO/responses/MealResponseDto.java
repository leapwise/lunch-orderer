package com.leapwise.lunchorderer.models.DTO.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealResponseDto {

    private long id;
    private String name;
    private double price;

}
