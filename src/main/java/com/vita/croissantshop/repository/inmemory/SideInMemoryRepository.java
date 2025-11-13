package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Side;
import com.vita.croissantshop.repository.SideRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SideInMemoryRepository implements SideRepository {
    private static final List<Side> SIDES = List.of(
            Side.builder()
                    .id(1L)
                    .name("French Fries")
                    .image("fries.png")
                    .price(2.0)
                    .build(),
            Side.builder()
                    .id(2L)
                    .name("Onion Rings")
                    .image("onion.png")
                    .price(1.0)
                    .build(),
            Side.builder()
                    .id(3L)
                    .name("Coleslaw")
                    .image("coleslaw.jpg")
                    .price(1.5)
                    .build(),
            Side.builder()
                    .id(4L)
                    .name("Mac-n-Cheese")
                    .image("mac_n_cheese.png")
                    .price(3.0)
                    .build(),
            Side.builder()
                    .id(5L)
                    .name("Mashed Potatoes")
                    .image("mashed_potatoes.png")
                    .price(2.0)
                    .build(),
            Side.builder()
                    .id(6L)
                    .name("Fruit Cup")
                    .image("fruit_cup.png")
                    .price(1.0)
                    .build(),
            Side.builder()
                    .id(7L)
                    .name("Baked Potato")
                    .image("baked_potato.png")
                    .price(1.5)
                    .build(),
            Side.builder()
                    .id(8L)
                    .name("Side Salad")
                    .image("side_salad.png")
                    .price(3.0)
                    .build(),
            Side.builder()
                    .id(9L)
                    .name("Mozzarella Sticks")
                    .image("moz_sticks.png")
                    .price(3.0)
                    .build()
    );

    private static final Map<Long, Side> ID_TO_SIDE_MAP = SIDES.stream()
            .collect(Collectors.toMap(Side::getId, side -> side));

    @Override
    public List<Side> getAllSides() {
        return SIDES;
    }

    @Override
    public Optional<Side> findById(Long sideId) {
        return Optional.ofNullable(ID_TO_SIDE_MAP.get(sideId));
    }
}
