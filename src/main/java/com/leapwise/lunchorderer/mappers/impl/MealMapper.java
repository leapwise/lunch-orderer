package com.leapwise.lunchorderer.mappers.impl;

import com.leapwise.lunchorderer.domain.dto.MealDto;
import com.leapwise.lunchorderer.domain.entities.MealEntity;
import com.leapwise.lunchorderer.mappers.Mapper;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between MealEntity and MealDto objects.
 */
@Component
public class MealMapper implements Mapper<MealEntity, com.leapwise.lunchorderer.domain.dto.MealDto> {

    /**
     * Maps a {@link MealEntity} to a {@link MealDto}.
     *
     * @param mealEntity The MealEntity to be mapped.
     * @return The corresponding MealDto.
     */
    @Override
    public MealDto mapTo(MealEntity mealEntity) {
        if (mealEntity == null)
            return null;
        com.leapwise.lunchorderer.domain.dto.MealDto mealDto = new com.leapwise.lunchorderer.domain.dto.MealDto();
        mealDto.setId(mealEntity.getId());
        mealDto.setName(mealEntity.getName());
        mealDto.setPrice(mealEntity.getPrice());
        return mealDto;
    }

    /**
     * Maps a {@link MealDto} to a {@link MealEntity}.
     *
     * @param mealDto The MealDto to be mapped.
     * @return The corresponding MealEntity.
     */
    @Override
    public MealEntity mapFrom(com.leapwise.lunchorderer.domain.dto.MealDto mealDto) {
        if (mealDto == null)
            return null;
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(mealDto.getId());
        mealEntity.setName(mealDto.getName());
        mealEntity.setPrice(mealDto.getPrice());
        return mealEntity;
    }
}

