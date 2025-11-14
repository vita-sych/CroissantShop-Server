package com.vita.croissantshop.service;

import com.vita.croissantshop.model.*;
import com.vita.croissantshop.repository.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReceiptServiceTest {

    private ReceiptRepository receiptRepository;
    private ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        receiptRepository = mock(ReceiptRepository.class);
        receiptService = new ReceiptService(receiptRepository);
    }

    @Test
    void testAddReceipt_buildsCorrectReceipt() {
        // Arrange
        Item sweetSmall = Item.builder()
                .type(Type.SWEET)
                .size(Size.SMALL)
                .toppings(List.of(
                        Topping.builder().name("Chocolate").price(1.0).build(),
                        Topping.builder().name("Almond").price(2.0).build()
                ))
                .build();

        OrderDrink latte = OrderDrink.builder()
                .quantity(1)
                .drink(Drink.builder().name("Latte").price(4.5).build())
                .build();

        OrderSide salad = OrderSide.builder()
                .quantity(2)
                .side(Side.builder().name("Salad").price(3).build())
                .build();

        Order order = Order.builder()
                .items(List.of(sweetSmall))
                .drinks(List.of(latte))
                .sides(List.of(salad))
                .build();

        // Act
        Receipt result = receiptService.addReceipt(order);

        // Capture argument passed to save()
        ArgumentCaptor<Receipt> captor = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepository, times(1)).save(captor.capture());
        Receipt savedReceipt = captor.getValue();

        // Assert
        assertThat(savedReceipt).isNotNull();
        assertThat(savedReceipt.getItems()).hasSize(1);
        assertThat(savedReceipt.getDrinks()).hasSize(1);
        assertThat(savedReceipt.getSides()).hasSize(1);

        // Price calculations:
        // SWEET SMALL base = 2
        // toppings = 1 + 2 = 3
        // item subtotal = 5
        // drinks = 4.5
        // sides = 3.0 * 2 = 6
        // subtotal = 5 + 4.5 + 3 = 12.5
        double expectedSubTotal = 15.5;
        double expectedTax = expectedSubTotal * 0.12; // 1.5
        double expectedShipping = 7;
        double expectedTotal = expectedSubTotal + expectedTax + expectedShipping;

        assertThat(savedReceipt.getSubTotal()).isEqualTo(expectedSubTotal);
        assertThat(savedReceipt.getTaxPrice()).isEqualTo(expectedTax);
        assertThat(savedReceipt.getShippingPrice()).isEqualTo(expectedShipping);
        assertThat(savedReceipt.getTotalPrice()).isEqualTo(expectedTotal);

        // Toppings mapping
        List<ReceiptSideEntry> toppingEntries = savedReceipt.getItems()
                .get(0).getToppings();
        assertThat(toppingEntries).hasSize(2);
        assertThat(toppingEntries.get(0).getName()).isEqualTo("Chocolate");
        assertThat(toppingEntries.get(1).getName()).isEqualTo("Almond");

        // Drink entry correct
        ReceiptSideEntry drinkEntry = savedReceipt.getDrinks().get(0);
        assertThat(drinkEntry.getName()).isEqualTo("Latte");
        assertThat(drinkEntry.getPrice()).isEqualTo(4.5);

        // Side entry correct
        ReceiptSideEntry sideEntry = savedReceipt.getSides().get(0);
        assertThat(sideEntry.getName()).isEqualTo("Salad");
        assertThat(sideEntry.getPrice()).isEqualTo(6.0);

        // Date populated
        assertThat(savedReceipt.getDate()).isNotNull();
        assertThat(savedReceipt.getDate()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void testShippingFreeWhenSubtotalOver50() {
        // Arrange
        Item expensive = Item.builder()
                .type(Type.SAVORY)
                .size(Size.LARGE)
                .toppings(List.of(
                        Topping.builder().name("Ham").price(45).build()
                ))
                .build();

        Order order = Order.builder()
                .items(List.of(expensive))
                .drinks(List.of())
                .sides(List.of())
                .build();

        // Act
        Receipt receipt = receiptService.addReceipt(order);

        // SAVORY LARGE = 6 + toppings 45 = 51 subtotal → free shipping

        assertThat(receipt.getShippingPrice()).isZero();
        assertThat(receipt.getSubTotal()).isEqualTo(51);
        assertThat(receipt.getTotalPrice())
                .isEqualTo(51 + 51 * 0.12); // only subtotal + tax
    }

    @Test
    void testZeroItemsHandlesGracefully() {
        Order emptyOrder = Order.builder()
                .items(List.of())
                .drinks(List.of())
                .sides(List.of())
                .build();

        Receipt receipt = receiptService.addReceipt(emptyOrder);

        assertThat(receipt.getSubTotal()).isZero();
        assertThat(receipt.getShippingPrice()).isEqualTo(7);
        assertThat(receipt.getTotalPrice()).isEqualTo(7); // shipping only
        verify(receiptRepository).save(any());
    }
}
