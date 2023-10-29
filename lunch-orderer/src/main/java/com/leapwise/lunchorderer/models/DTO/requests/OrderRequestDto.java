package com.leapwise.lunchorderer.models.DTO.requests;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private List<Long> mealIds;
}
