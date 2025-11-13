package com.vita.croissantshop.repository;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Order;
import com.vita.croissantshop.model.OrderDrink;
import com.vita.croissantshop.model.OrderSide;

import java.util.List;
import java.util.Optional;

public interface NewOrderRepository {

    Optional<Order> getCurrentOrder();

    Order startNewOrder();

    void deleteOrder();

    Order addDrinks(List<OrderDrink> drinks);

    Order addSides(List<OrderSide> sides);

    Order addItem(Item item);

    void createReceipt();
}

