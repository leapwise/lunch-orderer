package com.leapwise.lunchorderer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leapwise.lunchorderer.TestDataUtil;
import com.leapwise.lunchorderer.domain.entities.MealEntity;
import com.leapwise.lunchorderer.services.MealService;
import com.leapwise.lunchorderer.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class OrderControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private OrderService orderService;

    private MealService mealService;

    @Autowired
    public OrderControllerIntegrationTests(MockMvc mockMvc, OrderService orderService, MealService mealService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.orderService = orderService;
        this.mealService = mealService;
    }

    @Test
    public void testThatCreateOrderReturnsHttpsStatus201Created() throws Exception {
        MealEntity mealEntityA = TestDataUtil.createTestMealEntityA();
        MealEntity mealEntityB = TestDataUtil.createTestMealEntityB();
        mealService.saveMeal(mealEntityA);
        mealService.saveMeal(mealEntityB);
        List<Long> mealIds = Arrays.asList(mealEntityA.getId(), mealEntityB.getId());

        String mealIdsJson = objectMapper.writeValueAsString(mealIds);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mealIdsJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatUpdateOrderReturnsHttpsStatus200Ok() throws Exception {
        MealEntity mealEntityA = TestDataUtil.createTestMealEntityA();
        MealEntity mealEntityB = TestDataUtil.createTestMealEntityB();
        MealEntity mealEntityC = TestDataUtil.createTestMealEntityC();
        MealEntity mealEntityD = TestDataUtil.createTestMealEntityD();
        mealService.saveMeal(mealEntityA);
        mealService.saveMeal(mealEntityB);
        mealService.saveMeal(mealEntityC);
        mealService.saveMeal(mealEntityD);

        List<Long> mealIds = Arrays.asList(mealEntityA.getId(), mealEntityB.getId());
        Long orderId = orderService.saveOrder(mealIds);
        List<Long> mealIdsNew = Arrays.asList(mealEntityC.getId(), mealEntityD.getId());
        orderService.updateOrder(orderId, mealIdsNew);

        String mealIdsNewJson = objectMapper.writeValueAsString(mealIdsNew);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/order/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mealIdsNewJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateOrderReturnsUpdatedOrder() throws Exception {
        MealEntity mealEntityA = TestDataUtil.createTestMealEntityA();
        MealEntity mealEntityB = TestDataUtil.createTestMealEntityB();
        MealEntity mealEntityC = TestDataUtil.createTestMealEntityC();
        MealEntity mealEntityD = TestDataUtil.createTestMealEntityD();
        mealService.saveMeal(mealEntityA);
        mealService.saveMeal(mealEntityB);
        mealService.saveMeal(mealEntityC);
        mealService.saveMeal(mealEntityD);

        List<Long> mealIds = Arrays.asList(mealEntityA.getId(), mealEntityB.getId());
        Long orderId = orderService.saveOrder(mealIds);
        List<Long> mealIdsNew = Arrays.asList(mealEntityC.getId(), mealEntityD.getId());
        orderService.updateOrder(orderId, mealIdsNew);
        String mealIdsNewJson = objectMapper.writeValueAsString(mealIdsNew);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/order/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mealIdsNewJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[*].meal.id")
                .value(contains(
                        mealEntityC.getId().intValue(),
                        mealEntityD.getId().intValue()
                ))
        );
    }
}
