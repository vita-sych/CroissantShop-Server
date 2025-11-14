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
                    .name("Kombucha")
                    .image("kombucha.jpeg")
                    .price(3.5)
                    .build(),
            Drink.builder()
                    .id(2L)
                    .name("Coca Cola")
                    .image("cola.png")
                    .price(1.5)
                    .build(),
            Drink.builder()
                    .id(3L)
                    .name("Fanta")
                    .image("fanta.jpg")
                    .price(1.5)
                    .build(),
            Drink.builder()
                    .id(4L)
                    .name("Sprite")
                    .image("sprite.png")
                    .price(1.5)
                    .build(),
            Drink.builder()
                    .id(5L)
                    .name("Latte")
                    .image("latte.jpg")
                    .price(4.5)
                    .build(),
            Drink.builder()
                    .id(6L)
                    .name("Cappuccino")
                    .image("cappuccino.png")
                    .price(4.0)
                    .build(),
            Drink.builder()
                    .id(7L)
                    .name("Mocha Latte")
                    .image("mocha_latte.png")
                    .price(5.0)
                    .build(),
            Drink.builder()
                    .id(8L)
                    .name("Black Tea")
                    .image("black_tea.png")
                    .price(2.5)
                    .build(),
            Drink.builder()
                    .id(9L)
                    .name("Rose Tea")
                    .image("rose_tea.jpeg")
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
