package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Drink;
import com.vita.croissantshop.repository.DrinkRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DrinkInMemoryRepository implements DrinkRepository {
    private static final List<Drink> DRINKS = List.of(
            Drink.builder()
                    .id(1L)
                    .name("Kombucha 77")
                    .description("Kombucha description")
                    .image("kombucha.jpeg")
                    .price(2.0)
                    .build(),
            Drink.builder()
                    .id(2L)
                    .name("Coca Cola")
                    .description("Coca cola description")
                    .image("cola.png")
                    .price(1.0)
                    .build(),
            Drink.builder()
                    .id(3L)
                    .name("Fanta")
                    .description("Fanta description")
                    .image("fanta.jpg")
                    .price(1.5)
                    .build(),
            Drink.builder()
                    .id(4L)
                    .name("Sprite")
                    .description("Sprite description")
                    .image("sprite.png")
                    .price(3.0)
                    .build()
    );

    private static final Map<Long, Drink> ID_TO_DRINK_MAP = DRINKS.stream()
            .collect(Collectors.toMap(Drink::getId, drink -> drink));

    @Override
    public List<Drink> getAllDrinks() {
        return DRINKS;
    }

    @Override
    public Optional<Drink> findById(Long drinkId) {
        return Optional.ofNullable(ID_TO_DRINK_MAP.get(drinkId));
    }
}
