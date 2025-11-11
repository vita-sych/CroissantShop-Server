package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Topping implements PricedItem {
    private Long id;
    private String name;
    private String image;
    private double price;
    private Type type;
}
