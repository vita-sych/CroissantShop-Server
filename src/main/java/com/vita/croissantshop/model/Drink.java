package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Drink implements PricedItem {
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
}
