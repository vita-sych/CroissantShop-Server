package com.vita.croissantshop.controller;

import com.vita.croissantshop.model.Drink;
import com.vita.croissantshop.repository.DrinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DrinkController {

    private DrinkRepository drinkRepository;

    @GetMapping("/drinks")
    public List<Drink> getDrinks() {
        return drinkRepository.getAllDrinks();
    }
}
