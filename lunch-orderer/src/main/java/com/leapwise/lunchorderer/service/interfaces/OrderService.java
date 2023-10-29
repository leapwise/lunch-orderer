package com.leapwise.lunchorderer.service.interfaces;

import com.leapwise.lunchorderer.models.DTO.requests.OrderRequestDto;
import com.leapwise.lunchorderer.models.DTO.responses.OrderResponseDto;

public interface OrderService {

    long createOrder(OrderRequestDto requestDto);

    OrderResponseDto updateOrder(OrderRequestDto requestDto, Long id);

    OrderResponseDto getOrderById(Long id);

    OrderResponseDto getLatestOrder();
}
