package com.vita.croissantshop.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddAdditionRequest {
    private List<AddAdditionRequestEntry> additionEntries;
}
