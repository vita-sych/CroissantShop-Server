package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderSide implements PricedItem {
    private Side side;
    private Integer quantity;

    @Override
    public double getPrice() {
        return quantity * side.getPrice();
    }
}
