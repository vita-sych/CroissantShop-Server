package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderDrink implements PricedItem{
    private Drink drink;
    private Integer quantity;

    @Override
    public double getPrice() {
        return quantity * drink.getPrice();
    }
}
