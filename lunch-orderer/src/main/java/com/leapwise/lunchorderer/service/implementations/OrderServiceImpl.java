package com.leapwise.lunchorderer.service.implementations;

import com.leapwise.lunchorderer.models.DTO.requests.OrderRequestDto;
import com.leapwise.lunchorderer.models.DTO.responses.OrderResponseDto;
import com.leapwise.lunchorderer.models.domain.Meal;
import com.leapwise.lunchorderer.models.domain.Order;
import com.leapwise.lunchorderer.repository.OrderRepository;
import com.leapwise.lunchorderer.service.interfaces.MealService;
import com.leapwise.lunchorderer.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.hibernate.Hibernate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MealService mealService;
    private final ModelMapper mapper;

    public long createOrder(OrderRequestDto requestDto) {
        List<Meal> meals = mealService.getMealsById(requestDto.getMealIds());
        LocalDateTime now = LocalDateTime.now();

        Order order = new Order();
        order.setMeals(meals);
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        orderRepository.save(order);
        return order.getId();
    }

    public OrderResponseDto updateOrder(OrderRequestDto requestDto, Long id)
    {
        Optional<Order> order = orderRepository.findById(id);
        LocalDateTime now = LocalDateTime.now();

        Order foundOrder = order.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with request id found"));

        List<Meal> meals = mealService.getMealsById(requestDto.getMealIds());

        foundOrder.getMeals().addAll(meals);
        foundOrder.setUpdatedAt(now);
        orderRepository.save(foundOrder);

        return mapper.map(foundOrder, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        Order foundOrder = order.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with request id found"));

        return mapper.map(foundOrder, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto getLatestOrder() {
        Optional<Order> latestOrder = Optional.ofNullable(orderRepository.findLatestOrder().get(0));

        Order foundOrder = latestOrder.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with request id found"));

        return mapper.map(foundOrder, OrderResponseDto.class);
    }
}
