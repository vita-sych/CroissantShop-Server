package com.vita.croissantshop.repository;

import com.vita.croissantshop.model.Side;

import java.util.List;
import java.util.Optional;

public interface SideRepository {

    List<Side> getAllSides();

    Optional<Side> findById(Long sideId);
}

