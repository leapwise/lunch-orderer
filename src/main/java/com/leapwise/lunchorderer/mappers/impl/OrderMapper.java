package com.leapwise.lunchorderer.mappers.impl;

import com.leapwise.lunchorderer.domain.dto.OrderDto;
import com.leapwise.lunchorderer.domain.dto.OrderItemDto;
import com.leapwise.lunchorderer.domain.entities.OrderEntity;
import com.leapwise.lunchorderer.domain.entities.OrderItemEntity;
import com.leapwise.lunchorderer.mappers.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Component responsible for mapping between OrderEntity and OrderDto objects.
 */
@Component
public class OrderMapper implements Mapper<OrderEntity, OrderDto> {

    private final OrderItemMapper orderItemMapper;

    /**
     * Constructs a new OrderMapper.
     *
     * @param orderItemMapper The mapper used for converting between OrderItemEntity and OrderItemDto.
     */
    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    /**
     * Maps an {@link OrderEntity} to an {@link OrderDto}.
     *
     * @param orderEntity The OrderEntity to be mapped.
     * @return The corresponding OrderDto.
     */
    @Override
    public OrderDto mapTo(OrderEntity orderEntity) {
        if (orderEntity == null)
            return null;
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        List<OrderItemDto> orderItems = orderEntity.getOrderItems().stream()
                                        .map(orderItemMapper::mapTo)
                                        .filter(Objects::nonNull)
                                        .toList();
        orderDto.setOrderItems(orderItems);
        return orderDto;
    }

    /**
     * Maps an {@link OrderDto} to an {@link OrderEntity}.
     *
     * @param orderDto The OrderDto to be mapped.
     * @return The corresponding OrderEntity.
     */
    @Override
    public OrderEntity mapFrom(OrderDto orderDto) {
        if (orderDto == null)
            return null;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        List<OrderItemEntity> meals = orderDto.getOrderItems().stream()
                                        .map(orderItemMapper::mapFrom)
                                        .filter(Objects::nonNull)
                                        .toList();
        orderEntity.setOrderItems(meals);
        return orderEntity;
    }
}
