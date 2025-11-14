package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Topping;
import com.vita.croissantshop.model.Type;
import com.vita.croissantshop.repository.ToppingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToppingInMemoryRepository implements ToppingRepository {
    private static final List<Topping> TOPPINGS = List.of(
            Topping.builder()
                    .id(1L)
                    .name("Ham")
                    .price(2.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(2L)
                    .name("Turkey")
                    .price(2.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(3L)
                    .name("Mushrooms")
                    .price(1.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(4L)
                    .name("Smoked Salmon")
                    .price(3.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(5L)
                    .name("Tomato Slices")
                    .price(1.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(6L)
                    .name("Spinach")
                    .price(1.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(7L)
                    .name("Avocado")
                    .price(2.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(8L)
                    .name("Paprika")
                    .price(0.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(9L)
                    .name("Mozzarella")
                    .price(1.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(10L)
                    .name("Pesto")
                    .price(0.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(11L)
                    .name("Dijon Mustard")
                    .price(0.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(12L)
                    .name("Mayonnaise")
                    .price(0.5)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(13L)
                    .name("Olives")
                    .price(1.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(14L)
                    .name("Arugula")
                    .price(1.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(15L)
                    .name("Prosciutto")
                    .price(3.0)
                    .type(Type.SAVORY)
                    .build(),
            Topping.builder()
                    .id(16L)
                    .name("Caramel")
                    .price(1.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(17L)
                    .name("Chocolate")
                    .price(1.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(18L)
                    .name("Strawberry")
                    .price(1.5)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(19L)
                    .name("Blueberry")
                    .price(1.5)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(21L)
                    .name("Nutella")
                    .price(2.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(22L)
                    .name("Caramel Sauce")
                    .price(1.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(23L)
                    .name("Vanilla Custard")
                    .price(2.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(24L)
                    .name("Whipped Cream")
                    .price(1.5)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(25L)
                    .name("Banana")
                    .price(1.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(26L)
                    .name("Mascarpone")
                    .price(2.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(27L)
                    .name("Hazelnut Spread")
                    .price(2.5)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(28L)
                    .name("Coconut Flakes")
                    .price(1.0)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(29L)
                    .name("Mixed Berry Jam")
                    .price(1.5)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(30L)
                    .name("Cherry glaze")
                    .price(1.5)
                    .type(Type.SWEET)
                    .build(),
            Topping.builder()
                    .id(31L)
                    .name("Cream Cheese")
                    .price(1.5)
                    .type(Type.SWEET)
                    .build()
    );

    @Override
    public List<Topping> findByType(Type type) {
        return TOPPINGS.stream().filter(topping -> topping.getType().equals(type)).toList();
    }
}
