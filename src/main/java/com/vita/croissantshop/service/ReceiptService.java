package com.vita.croissantshop.service;

import com.vita.croissantshop.model.Order;
import com.vita.croissantshop.model.PricedItem;
import com.vita.croissantshop.model.Topping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptService {

    public void addReceipt(Order order) {
        try {
            Path path = Paths.get("src/main/resources/receipts/");
            String fileName = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss").format(LocalDateTime.now()) + ".txt";

            String fullFilePath = path + File.separator +  fileName;
            FileWriter fileWriter = new FileWriter(fullFilePath, true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            String items = order.getItems().stream()
                    .map(item -> {
                        String toppings = item.getToppings().stream()
                                .map(t -> String.format("   - %s | $%s", t.getName(), t.getPrice()))
                                .collect(Collectors.joining("\n"));
                        return String.format("Item: %s | %s\nToppings:\n%s\n", item.getSize(), item.getType(), toppings);
                    })
                    .collect(Collectors.joining("\n"));

            String drinks = order.getDrinks().stream()
                    .map(d -> String.format("   - %s | $%s |Quantity: %d", d.getDrink().getName(), d.getPrice(), d.getQuantity()))
                    .collect(Collectors.joining("\n"));

            double orderSubTotal = getSubTotal(order);

            String content = String.format("""
            ==================================
            Order Receipt
            Date: %s
            ==================================

            %s
            
            Drinks:
            %s

            ==================================
            Sub Total: $%s
            Shipping: $%s
            Taxes: $%s
            ==================================
            Order Total: $%s
            ==================================
            """, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), items, drinks, orderSubTotal, getShipping(orderSubTotal), getTax(orderSubTotal), getTotal(orderSubTotal));

            bufWriter.write(content);
            bufWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private <T extends PricedItem> double getSubTotalByItem(List<T> items) {
        return items.stream()
                .mapToDouble(PricedItem::getPrice)
                .sum();
    }

    private double getSubTotal(Order order) {
        List<Topping> toppings = order.getItems().stream().flatMap(item -> item.getToppings().stream()).toList();
        return getSubTotalByItem(order.getDrinks()) + getSubTotalByItem(toppings);
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
