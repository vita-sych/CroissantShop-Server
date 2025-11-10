package com.vita.croissantshop.controller;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Order;
import com.vita.croissantshop.model.OrderDrink;
import com.vita.croissantshop.model.request.AddDrinksRequest;
import com.vita.croissantshop.repository.DrinkRepository;
import com.vita.croissantshop.repository.NewOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewOrderController {

    private NewOrderRepository newOrderRepository;
    private DrinkRepository drinkRepository;

    @GetMapping("/new-order")
    public Optional<Order> getCurrentOrder() {
        return newOrderRepository.getCurrentOrder();
    }

    @PostMapping("/new-order")
    public Order startNewOrder() {
        return newOrderRepository.startNewOrder();
    }

    @DeleteMapping("/new-order")
    public void deleteOrder() {
        newOrderRepository.deleteOrder();
    }

    @PostMapping("/new-order/drinks")
    public Order addDrink(@RequestBody AddDrinksRequest addDrinksRequest) {
        List<OrderDrink> orderDrinks = addDrinksRequest.getDrinksEntries().stream()
                .map(requestEntry -> OrderDrink.builder()
                        .drink(drinkRepository.findById(requestEntry.getDrinkId()).orElseThrow())
                        .quantity(requestEntry.getQuantity())
                        .build()
                ).toList();
        return newOrderRepository.addDrinks(orderDrinks);
    }

    @PostMapping("/new-order/item")
    public Order addItem(@RequestBody Item item) {
        return newOrderRepository.addItem(item);
    }
}

