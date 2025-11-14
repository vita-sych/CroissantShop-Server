package com.vita.croissantshop.controller;

import com.vita.croissantshop.model.Item;
import com.vita.croissantshop.model.Type;
import com.vita.croissantshop.repository.SignatureRepository;
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
public class SignatureController {
    private SignatureRepository signatureRepository;

    // GET /signatures?type=sweet
    @GetMapping("/signatures")
    public List<Item> getSignaturesByType(@RequestParam Type type) {
        return signatureRepository.findByType(type);
    }
}


