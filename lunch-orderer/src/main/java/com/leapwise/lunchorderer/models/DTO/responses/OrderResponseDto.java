package com.leapwise.lunchorderer.models.DTO.responses;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {

    private long id;

    private List<MealResponseDto> meals;
}
