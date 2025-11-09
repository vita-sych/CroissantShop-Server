package com.vita.croissantshop.repository;

import com.vita.croissantshop.model.Drink;

import java.util.List;
import java.util.Optional;

public interface DrinkRepository {

    List<Drink> getAllDrinks();

    Optional<Drink> findById(Long drinkId);
}

