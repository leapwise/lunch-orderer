package com.leapwise.lunchorderer.mappers.impl;

import com.leapwise.lunchorderer.domain.dto.OrderItemDto;
import com.leapwise.lunchorderer.domain.entities.OrderItemEntity;
import com.leapwise.lunchorderer.mappers.Mapper;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between OrderItemEntity and OrderItemDto objects.
 */
@Component
public class OrderItemMapper implements Mapper<OrderItemEntity, OrderItemDto> {

    /**
     * Maps an {@link OrderItemEntity} to an {@link OrderItemDto}.
     *
     * @param orderItemEntity The OrderItemEntity to be mapped.
     * @return The corresponding OrderItemDto.
     */
    @Override
    public OrderItemDto mapTo(OrderItemEntity orderItemEntity) {
        if (orderItemEntity == null)
            return null;
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItemEntity.getId());
        orderItemDto.setMeal(orderItemEntity.getMeal());
        orderItemDto.setQuantity(orderItemEntity.getQuantity());
        return orderItemDto;
    }

    /**
     * Maps an {@link OrderItemDto} to an {@link OrderItemEntity}.
     *
     * @param orderItemDto The OrderItemDto to be mapped.
     * @return The corresponding OrderItemEntity.
     */
    @Override
    public OrderItemEntity mapFrom(OrderItemDto orderItemDto) {
        if (orderItemDto == null)
            return null;
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(orderItemDto.getId());
        orderItemEntity.setMeal(orderItemDto.getMeal());
        orderItemEntity.setQuantity(orderItemDto.getQuantity());
        return orderItemEntity;
    }
}
