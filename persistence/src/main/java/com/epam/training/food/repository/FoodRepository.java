package com.epam.training.food.repository;

import com.epam.training.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
