package com.vita.croissantshop.controller;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Order;
import com.vita.croissantshop.model.OrderDrink;
import com.vita.croissantshop.model.OrderSide;
import com.vita.croissantshop.model.Receipt;
import com.vita.croissantshop.model.request.AddAdditionRequest;
import com.vita.croissantshop.repository.DrinkRepository;
import com.vita.croissantshop.repository.NewOrderRepository;
import com.vita.croissantshop.repository.SideRepository;
import com.vita.croissantshop.service.ReceiptService;
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
    private SideRepository sideRepository;
    private ReceiptService receiptService;

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
    public Order addDrinks(@RequestBody AddAdditionRequest addDrinksRequest) {
        List<OrderDrink> orderDrinks = addDrinksRequest.getAdditionEntries().stream()
                .map(requestEntry -> OrderDrink.builder()
                        .drink(drinkRepository.findById(requestEntry.getAdditionId()).orElseThrow())
                        .quantity(requestEntry.getQuantity())
                        .build()
                ).toList();
        return newOrderRepository.addDrinks(orderDrinks);
    }

    @PostMapping("/new-order/sides")
    public Order addSides(@RequestBody AddAdditionRequest addSidesRequest) {
        List<OrderSide> orderSides = addSidesRequest.getAdditionEntries().stream()
                .map(requestEntry -> OrderSide.builder()
                        .side(sideRepository.findById(requestEntry.getAdditionId()).orElseThrow())
                        .quantity(requestEntry.getQuantity())
                        .build()
                ).toList();
        return newOrderRepository.addSides(orderSides);
    }

    @PostMapping("/new-order/item")
    public Order addItem(@RequestBody Item item) {
        return newOrderRepository.addItem(item);
    }

    @PostMapping("/new-order/receipt")
    public Receipt confirmOrder() {
        Order order = newOrderRepository.getCurrentOrder().orElseThrow();
        Receipt receipt = receiptService.addReceipt(order);
        newOrderRepository.deleteOrder();
        return receipt;
    }
}

