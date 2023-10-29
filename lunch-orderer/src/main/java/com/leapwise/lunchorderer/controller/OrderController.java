package com.leapwise.lunchorderer.controller;

import com.leapwise.lunchorderer.models.DTO.requests.OrderRequestDto;
import com.leapwise.lunchorderer.models.DTO.responses.OrderResponseDto;
import com.leapwise.lunchorderer.service.interfaces.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public long createOrder(@RequestBody @NonNull OrderRequestDto requestDto)
    {
        return orderService.createOrder(requestDto);
    }

    @PutMapping("/order/{id}")
    public OrderResponseDto updateOrder(@RequestBody @NonNull OrderRequestDto requestDto, @PathVariable @NonNull Long id)
    {
        return orderService.updateOrder(requestDto,id);
    }

    @GetMapping("/get/order/{id}")
    public OrderResponseDto getOrder(@PathVariable @NonNull Long id)
    {
        return orderService.getOrderById(id);
    }

}
