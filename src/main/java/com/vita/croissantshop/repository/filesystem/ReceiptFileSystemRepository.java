package com.vita.croissantshop.repository.filesystem;

import com.vita.croissantshop.model.Receipt;
import com.vita.croissantshop.model.ReceiptSideEntry;
import com.vita.croissantshop.repository.ReceiptRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReceiptFileSystemRepository implements ReceiptRepository {
    @Override
    public void save(Receipt receipt) {
        System.out.println("Saved to file system!");
        try {
            Path path = Paths.get("src/main/resources/receipts/");
            String fileName = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss").format(receipt.getDate()) + ".txt";

            String fullFilePath = path + File.separator + fileName;
            FileWriter fileWriter = new FileWriter(fullFilePath, true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            String items = receipt.getItems().stream()
                    .map(item -> {
                        String toppings = item.getToppings().stream()
                                .map(t -> String.format("   - %s | $%.2f", t.getName(), t.getPrice()))
                                .collect(Collectors.joining("\n"));
                        return String.format("\nCroissant: %s | %s | $%s\nToppings:\n%s", item.getSize(), item.getType(),item.getPrice(), toppings);
                    })
                    .collect(Collectors.joining("\n"));

            String drinks = !receipt.getDrinks().isEmpty() ? "\nDrinks:" + "\n" + createSideEntryString(receipt.getDrinks()) : "";
            String sides = !receipt.getSides().isEmpty() ? "\nSides:" + "\n" + createSideEntryString(receipt.getSides()) : "";

            double orderSubTotal = receipt.getSubTotal();

            String content = String.format("""
                    ==================================
                    Order Receipt
                    Date: %s
                    ==================================
                    %s%s%s
                    
                    ==================================
                    Sub Total: $%.2f
                    Shipping: $%.2f
                    Taxes: $%.2f
                    ==================================
                    Order Total: $%.2f
                    ==================================
                    """, receipt.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), items, sides, drinks, orderSubTotal, receipt.getShippingPrice(), receipt.getTaxPrice(), receipt.getTotalPrice());

            bufWriter.write(content);
            bufWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createSideEntryString(List<ReceiptSideEntry> entries) {
        return entries.stream()
                .map(d -> String.format("   - %s | $%s |Quantity: %d", d.getName(), d.getPrice(), d.getQuantity()))
                .collect(Collectors.joining("\n"));
    }
}
