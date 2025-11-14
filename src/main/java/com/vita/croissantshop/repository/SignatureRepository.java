package com.vita.croissantshop.repository;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Type;

import java.util.List;
import java.util.Optional;

public interface SignatureRepository {

    List<Item> getAllSignatures();

    Optional<Item> findById(Long itemId);

    List<Item> findByType(Type type);
}

