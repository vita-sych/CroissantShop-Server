package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItemEntry {
    private Size size;
    private Type type;
    private double price;
    private List<ReceiptSideEntry> toppings = new ArrayList<>();
}
