package com.vita.croissantshop.service;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Order;
import com.vita.croissantshop.model.PricedItem;
import com.vita.croissantshop.model.Receipt;
import com.vita.croissantshop.model.ReceiptItemEntry;
import com.vita.croissantshop.model.ReceiptSideEntry;
import com.vita.croissantshop.model.Size;
import com.vita.croissantshop.model.Topping;
import com.vita.croissantshop.model.Type;
import com.vita.croissantshop.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptService {

    private static final Map<Type, Map<Size, Double>> ITEM_PRICE = Map.of(
            Type.SWEET, Map.of(
                    Size.SMALL, 2d,
                    Size.MEDIUM, 3d,
                    Size.LARGE, 5d
            ),
            Type.SAVORY, Map.of(
                    Size.SMALL, 2.5d,
                    Size.MEDIUM, 4.0d,
                    Size.LARGE, 6.0d
            )
    );

    private final ReceiptRepository receiptRepository;

    public Receipt addReceipt(Order order) {
        List<ReceiptItemEntry> receiptItemEntries = generateReceiptItems(order);
        List<ReceiptSideEntry> receiptDrinkEntries = generateDrinksReceiptEntries(order);
        List<ReceiptSideEntry> receiptSideEntries = generateSidesReceiptEntries(order);
        double orderSubTotal = getSubTotal(order);

        Receipt receipt = Receipt.builder()
                .items(receiptItemEntries)
                .drinks(receiptDrinkEntries)
                .sides(receiptSideEntries)
                .drinksTotalPrice(getSubTotalByItem(order.getDrinks()))
                .sidesTotalPrice(getSubTotalByItem(order.getSides()))
                .subTotal(orderSubTotal)
                .shippingPrice(getShipping(orderSubTotal))
                .taxPrice(getTax(orderSubTotal))
                .totalPrice(getTotal(orderSubTotal))
                .date(LocalDateTime.now())
                .build();

        receiptRepository.save(receipt);
        return receipt;
    }

    private List<ReceiptSideEntry> generateSidesReceiptEntries(Order order) {
        return order.getSides().stream()
                .map(side -> ReceiptSideEntry.builder()
                        .name(side.getSide().getName())
                        .price(side.getPrice())
                        .quantity(side.getQuantity())
                        .build()
                ).toList();
    }

    private List<ReceiptSideEntry> generateDrinksReceiptEntries(Order order) {
        return order.getDrinks().stream()
                .map(drink -> ReceiptSideEntry.builder()
                        .name(drink.getDrink().getName())
                        .price(drink.getPrice())
                        .quantity(drink.getQuantity())
                        .build()
                ).toList();
    }

    private List<ReceiptItemEntry> generateReceiptItems(Order order) {
        return order.getItems().stream()
                .map(item -> ReceiptItemEntry.builder()
                        .type(item.getType())
                        .size(item.getSize())
                        .price(getSubTotalItem(item))
                        .toppings(generateToppingsReceipt(item))
                        .build()
                ).toList();
    }

    private List<ReceiptSideEntry> generateToppingsReceipt(Item item) {
        return item.getToppings().stream()
                .map(topping -> ReceiptSideEntry.builder()
                        .name(topping.getName())
                        .price(topping.getPrice())
                        .quantity(1)
                        .build()
                ).toList();
    }

    private <T extends PricedItem> double getSubTotalByItem(List<T> items) {
        return items.stream()
                .mapToDouble(PricedItem::getPrice)
                .sum();
    }

    private double getSubTotalItem(Item item) {
        return  ITEM_PRICE.get(item.getType()).get(item.getSize()) + getSubTotalByItem(item.getToppings());
    }

    private double getSubTotal(Order order) {
        double itemsPrice = order.getItems().stream().reduce(0d, (subtotal, item) -> subtotal + getSubTotalItem(item), Double::sum);
        return getSubTotalByItem(order.getDrinks()) + itemsPrice + getSubTotalByItem(order.getSides());
    }

    private double getShipping(double orderSubTotal) {
        return orderSubTotal > 50 ? 0 : 7;
    }

    private double getTax(double orderSubTotal) {
        return orderSubTotal * 0.12;
    }

    private double getTotal(double orderSubTotal) {
        return orderSubTotal + getTax(orderSubTotal) + getShipping(orderSubTotal);
    }
}
