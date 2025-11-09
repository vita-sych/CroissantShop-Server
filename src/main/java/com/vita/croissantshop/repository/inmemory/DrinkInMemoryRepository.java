package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Drink;
import com.vita.croissantshop.repository.DrinkRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrinkInMemoryRepository implements DrinkRepository {
    private static final List<Drink> DRINKS = List.of(
            Drink.builder()
                    .name("Kombucha 77")
                    .description("Kombucha description")
                    .image("")
                    .build(),
            Drink.builder()
                    .name("Coca Cola")
                    .description("Coca cola description")
                    .image("")
                    .build()
    );

    @Override
    public List<Drink> getAllDrinks() {
        return DRINKS;
    }
}
