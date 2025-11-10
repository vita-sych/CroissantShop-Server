package com.vita.croissantshop.controller;

import com.vita.croissantshop.model.Topping;
import com.vita.croissantshop.model.Type;
import com.vita.croissantshop.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ToppingController {
    private ToppingRepository toppingRepository;

    // GET /toppings?type=sweet
    @GetMapping("/toppings")
    public List<Topping> getToppingsByType(@RequestParam Type type) {
        return toppingRepository.findByType(type);
    }
}
