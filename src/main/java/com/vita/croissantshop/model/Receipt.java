package com.vita.croissantshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private List<ReceiptItemEntry> items;
    private List<ReceiptSideEntry> drinks;
    private List<ReceiptSideEntry> sides;
    private double drinksTotalPrice;
    private double sidesTotalPrice;
    private double subTotal;
    private double shippingPrice;
    private double taxPrice;
    private double totalPrice;
    private LocalDateTime date;
}
