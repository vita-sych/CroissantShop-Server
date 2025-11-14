package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Size;
import com.vita.croissantshop.model.Topping;
import com.vita.croissantshop.model.Type;
import com.vita.croissantshop.repository.SignatureRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SignatureInMemoryRepository implements SignatureRepository {
    private static final List<Item> SIGNATURES = List.of(
            Item.builder()
                    .id(1L)
                    .name("Strawberry & Mascarpone")
                    .size(Size.MEDIUM)
                    .type(Type.SWEET)
                    .image("mascarpone_signature.png")
                    .toppings(List.of(
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
                                    .build()
                    ))
                    .build(),
            Item.builder()
                    .id(2L)
                    .name("Almond & Blueberry")
                    .size(Size.MEDIUM)
                    .type(Type.SWEET)
                    .image("blueberry_signature.png")
                    .toppings(List.of(
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
                                    .build()
                    ))
                    .build(),
            Item.builder()
                    .id(3L)
                    .name("Nutella, Strawberry & Banana")
                    .size(Size.MEDIUM)
                    .type(Type.SWEET)
                    .image("nutella_signature.png")
                    .toppings(List.of(
                            Topping.builder()
                                    .id(25L)
                                    .name("Banana")
                                    .price(1.0)
                                    .type(Type.SWEET)
                                    .build()
                    ))
                    .build(),
            Item.builder()
                    .id(4L)
                    .name("Turkey & Avocado")
                    .size(Size.MEDIUM)
                    .type(Type.SAVORY)
                    .image("turkey_avocado.png")
                    .toppings(List.of(
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
                                    .build()
                    ))
                    .build(),
            Item.builder()
                    .id(5L)
                    .name("Cream Cheese")
                    .size(Size.MEDIUM)
                    .type(Type.SAVORY)
                    .image("cream_cheese.png")
                    .toppings(List.of(
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
                                    .build()
                    ))
                    .build(),
            Item.builder()
                    .id(6L)
                    .name("Veggie")
                    .size(Size.MEDIUM)
                    .type(Type.SAVORY)
                    .image("veggie.png")
                    .toppings(List.of(
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
                                    .build()
                    ))
                    .build()
    );

    private static final Map<Long, Item> ID_TO_SIGNATURE_MAP = SIGNATURES.stream()
            .collect(Collectors.toMap(Item::getId, item -> item));

    @Override
    public List<Item> getAllSignatures() {
        return SIGNATURES;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
         return Optional.ofNullable(ID_TO_SIGNATURE_MAP.get(itemId));
    }

    @Override
    public List<Item> findByType(Type type) {
        return SIGNATURES.stream().filter(item -> item.getType().equals(type)).toList();
    }
}