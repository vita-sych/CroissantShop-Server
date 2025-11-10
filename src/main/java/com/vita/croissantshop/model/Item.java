package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Item {
    private Long id;
    private Size size;
    private Type type;
    @Builder.Default
    private List<Topping> toppings = new ArrayList<>();
}
