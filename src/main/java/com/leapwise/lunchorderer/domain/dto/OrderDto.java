package com.leapwise.lunchorderer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing an order.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    /**
     * The unique identifier for the order.
     */
    private Long id;

    /**
     * A list of {@link OrderItemDto} objects associated with this order.
     */
    private List<OrderItemDto> orderItems;
}
