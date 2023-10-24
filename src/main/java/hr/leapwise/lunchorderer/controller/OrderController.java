package hr.leapwise.lunchorderer.controller;

import hr.leapwise.lunchorderer.model.Order;
import hr.leapwise.lunchorderer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public Long createOrder(@RequestBody final List<Long> mealIds) {
        return orderService.createOrder(mealIds);
    }

    @PatchMapping("/order/{orderId}")
    public Order updateOrder(@PathVariable final Long orderId, @RequestBody final List<Long> mealIds) {
        return orderService.updateOrder(orderId, mealIds);
    }
}
