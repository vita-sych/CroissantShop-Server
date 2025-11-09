package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderDrink {
    private Drink drink;
    private Integer quantity;
}
