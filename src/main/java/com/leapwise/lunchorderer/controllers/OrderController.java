package com.leapwise.lunchorderer.controllers;

import com.leapwise.lunchorderer.domain.dto.OrderDto;
import com.leapwise.lunchorderer.domain.entities.OrderEntity;
import com.leapwise.lunchorderer.mappers.impl.OrderMapper;
import com.leapwise.lunchorderer.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling order-related requests.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    /**
     * Constructs a new OrderController.
     *
     * @param orderService The service responsible for managing orders.
     * @param orderMapper  The mapper used to convert between domain and DTO objects.
     */
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    /**
     * Creates a new order with the specified list of meal IDs.
     *
     * @param mealIds The list of meal IDs to include in the order.
     * @return A {@link ResponseEntity} object containing the ID of the created order,
     * or BAD_REQUEST if list of meals is empty or null.
     */
    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody List<Long> mealIds){
        if (mealIds == null || mealIds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long orderId = orderService.saveOrder(mealIds);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    /**
     * Updates an existing order with the specified ID.
     *
     * @param orderId  The ID of the order to be updated.
     * @param mealIds  The list of meal IDs to include in the order.
     * @return A {@link ResponseEntity} object containing the updated OrderDto if successful,
     * or NOT_FOUND if the order doesn't exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long orderId,
                                                @RequestBody List<Long> mealIds) {
        if (!orderService.exists(orderId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        OrderEntity orderEntity = orderService.updateOrder(orderId, mealIds);
        return new ResponseEntity<>(orderMapper.mapTo(orderEntity), HttpStatus.OK);
    }
}
