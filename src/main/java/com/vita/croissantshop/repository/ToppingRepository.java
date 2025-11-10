package com.vita.croissantshop.repository;

import com.vita.croissantshop.model.Topping;
import com.vita.croissantshop.model.Type;

import java.util.List;

public interface ToppingRepository {
    List<Topping> findByType(Type type);
}
