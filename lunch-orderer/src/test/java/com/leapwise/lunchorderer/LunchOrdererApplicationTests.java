package com.leapwise.lunchorderer;

import com.leapwise.lunchorderer.controller.MealController;
import com.leapwise.lunchorderer.controller.OrderController;
import com.leapwise.lunchorderer.models.DTO.requests.OrderRequestDto;
import com.leapwise.lunchorderer.models.DTO.responses.MealResponseDto;
import com.leapwise.lunchorderer.models.DTO.responses.OrderResponseDto;
import com.leapwise.lunchorderer.models.domain.Meal;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class LunchOrdererApplicationTests {

    @Autowired
    private MealController mealController;
    @Autowired
    private OrderController orderController;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldInitDB()
    {
        List<MealResponseDto> meals = mealController.getMeals();

        Assertions.assertThat(meals.size()).isEqualTo(9);
    }
    @Test
    void shouldGetMeal()
    {
        String mealName = "Varivo od mix mahunarki";
        double mealPrice = 3.60;
        MealResponseDto meal = new MealResponseDto();
        meal.setName(mealName);
        meal.setPrice(mealPrice);
        
        MealResponseDto foundMeal = mealController.getMeal(1L);

        Assertions.assertThat(meal.getName()).isEqualTo(foundMeal.getName());
        
    }

    @Test
    void shouldCreateOrder()
    {
        OrderRequestDto requestDto = new OrderRequestDto();
        long orderId = createOrder(requestDto);

        OrderResponseDto responseDto = orderController.getOrder(orderId);

        List<Long> mealIds = responseDto.getMeals()
                .stream()
                .map(MealResponseDto::getId)
                .toList();

        Assertions.assertThat(requestDto.getMealIds()).isEqualTo(mealIds);
    }

    @Test
    void shouldUpdateOrder()
    {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        long orderId = createOrder(orderRequestDto);

        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setMealIds(Arrays.asList(4L, 5L, 6L));

        OrderResponseDto orderBeforeUpdate = orderController.getOrder(orderId);

        orderController.updateOrder(requestDto, orderId);

        OrderResponseDto orderAfterUpdate = orderController.getOrder(orderId);

        List<Long> idsBefore = orderBeforeUpdate.getMeals().stream()
                .map(MealResponseDto::getId)
                .toList();

        List<Long> idsAfter = orderAfterUpdate.getMeals().stream()
                .map(MealResponseDto::getId)
                .collect(Collectors.toList());

        List<Long> expectedIdsAfterUpdate = new ArrayList<>(idsBefore);
        expectedIdsAfterUpdate.addAll(requestDto.getMealIds());

        Assertions.assertThat(idsAfter).isEqualTo(expectedIdsAfterUpdate);
    }

    private long createOrder(OrderRequestDto requestDto){
        requestDto.setMealIds(Arrays.asList(1L, 2L, 3L));

        return orderController.createOrder(requestDto);
    }
}
