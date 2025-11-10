package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private List<OrderDrink> drinks = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
}
