package com.leapwise.lunchorderer.models.DTO;

import com.leapwise.lunchorderer.models.DTO.responses.MealResponseDto;

import java.util.List;

public class OrderDto {

    private long id;
    private List<MealResponseDto> meals;
}
