package com.vita.croissantshop.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddDrinksRequestEntry {
    private Long drinkId;
    private Integer quantity;
}
