package com.vita.croissantshop.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddAdditionRequestEntry {
    private Long additionId;
    private Integer quantity;
}
