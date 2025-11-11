package com.vita.croissantshop.repository.inmemory;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Order;
import com.vita.croissantshop.model.OrderDrink;
import com.vita.croissantshop.repository.NewOrderRepository;
import com.vita.croissantshop.service.ReceiptService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NewOrderInMemoryRepository implements NewOrderRepository {

    private Order currentOrder = null;
    private final ReceiptService receiptService = new ReceiptService();

    @Override
    public Optional<Order> getCurrentOrder() {
        return Optional.ofNullable(currentOrder);
    }

    @Override
    public Order startNewOrder() {
        currentOrder = new Order();
        return currentOrder;
    }

    @Override
    public void deleteOrder() {
        currentOrder = null;
    }

    @Override
    public Order addDrinks(List<OrderDrink> drinks) {
        for (OrderDrink drink : drinks) {
            mergeToOrder(drink, currentOrder);
        }
        return currentOrder;
    }

    @Override
    public Order addItem(Item item) {
        currentOrder.getItems().add(item);
        return currentOrder;
    }

    @Override
    public void createReceipt() {
        receiptService.addReceipt(currentOrder);
    }

    private void mergeToOrder(OrderDrink newDrink, Order currentOrder) {
        Optional<OrderDrink> existingDrink = currentOrder.getDrinks().stream()
                .filter(orderDrink -> newDrink.getDrink().getId().equals(orderDrink.getDrink().getId()))
                .findFirst();
        if (existingDrink.isPresent()) {
            existingDrink.get().setQuantity(existingDrink.get().getQuantity() + newDrink.getQuantity());
        } else {
            currentOrder.getDrinks().add(newDrink);
        }
    }
}
