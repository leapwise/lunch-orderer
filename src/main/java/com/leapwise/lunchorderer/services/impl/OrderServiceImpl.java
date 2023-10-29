package com.leapwise.lunchorderer.services.impl;

import com.leapwise.lunchorderer.domain.entities.OrderEntity;
import com.leapwise.lunchorderer.domain.entities.OrderItemEntity;
import com.leapwise.lunchorderer.repositories.OrderRepository;
import com.leapwise.lunchorderer.services.OrderItemService;
import com.leapwise.lunchorderer.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service implementation for managing orders.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    /**
     * Constructs a new OrderServiceImpl.
     *
     * @param orderRepository    The repository for accessing order data.
     * @param orderItemService   The service for managing order items.
     */
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    /**
     * Saves an order with the specified list of meal IDs.
     *
     * @param mealIds The list of meal IDs to include in the order.
     * @return The unique identifier (ID) of the created order.
     */
    @Override
    public Long saveOrder(List<Long> mealIds) {
        List<OrderItemEntity> orderItemEntities = createOrderItemEntityList(mealIds);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderItems(orderItemEntities);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return savedOrderEntity.getId();
    }

    /**
     * Retrieves an order by its unique identifier.
     *
     * @param orderId The unique identifier of the order to retrieve.
     * @return The corresponding {@link OrderEntity} object.
     */
    @Override
    public OrderEntity getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of all orders.
     */
    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Checks if an order with the specified ID exists.
     *
     * @param orderId The unique identifier of the order to check for existence.
     * @return true if the order exists, false otherwise.
     */
    @Override
    public boolean exists(Long orderId) {
        return orderRepository.existsById(orderId);
    }

    /**
     * Updates an existing order with the specified ID.
     *
     * @param orderId  The unique identifier of the order to be updated.
     * @param mealIds  The list of meal IDs to be associated with the order.
     * @return The updated OrderEntity.
     */
    @Override
    public OrderEntity updateOrder(Long orderId, List<Long> mealIds) {
        List<OrderItemEntity> orderItemEntities = createOrderItemEntityList(mealIds);
        OrderEntity orderEntity = getOrder(orderId);
        orderEntity.getOrderItems().clear();
        orderEntity.getOrderItems().addAll(orderItemEntities);
        return orderRepository.save(orderEntity);
    }

    /**
     * Creates a list of OrderItemEntity objects based on the specified list of meal IDs.
     *
     * @param mealIds The list of meal IDs and their corresponding quantities.
     * @return A list of OrderItemEntity objects.
     */
    private List<OrderItemEntity> createOrderItemEntityList(List<Long> mealIds) {
        Map<Long, Long> mealQuantity = mealIds.stream().collect(Collectors.groupingBy(id -> id, Collectors.counting()));
        return mealQuantity.entrySet().stream()
                .map(entry -> {
                    Long mealId = entry.getKey();
                    Long quantity = entry.getValue();
                    return orderItemService.createOrderItem(mealId, quantity.intValue());
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
