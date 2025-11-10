package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Topping;
import com.vita.croissantshop.model.Type;
import com.vita.croissantshop.repository.ToppingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToppingInMemoryRepository implements ToppingRepository {
    private static final List<Topping> toppings = List.of(
            Topping.builder()
                    .id(1L)
                    .name("meat")
                    .image("meat.jpg")
                    .price(8.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(2L)
                    .name("almond")
                    .image("almond.jpg")
                    .price(5.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(3L)
                    .name("mushroom")
                    .image("mushroom.jpg")
                    .price(17.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(4L)
                    .name("orange")
                    .image("orange.jpg")
                    .price(4.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(5L)
                    .name("berry")
                    .image("berry.jpg")
                    .price(5.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(6L)
                    .name("banana")
                    .image("banana.jpg")
                    .price(3.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(7L)
                    .name("chocolate")
                    .image("chocolate.jpg")
                    .price(1.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(8L)
                    .name("lettuce")
                    .image("lettuce.jpg")
                    .price(8.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(9L)
                    .name("cheese")
                    .image("cheese.jpg")
                    .price(9.0)
                    .type(Type.SAVORY)
                    .build()
    );

    @Override
    public List<Topping> findByType(Type type) {
        return toppings.stream().filter(topping -> topping.getType().equals(type)).toList();
    }
}
