package com.vita.croissantshop.controller;

import com.vita.croissantshop.model.Side;
import com.vita.croissantshop.repository.SideRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3003")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SideController {

    private SideRepository sideRepository;

    @GetMapping("/sides")
    public List<Side> getSides() {
        return sideRepository.getAllSides();
    }
}
