package com.example.apartment_predictor.controller;   // ← paquete correcto (crea la carpeta controller si no existe)

import com.example.apartment_predictor.service.PopulateService;   // ← import correcto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PopulateController {

    @Autowired
    private PopulateService populateService;

    @PostMapping("/populate")
    public ResponseEntity<String> populateDatabase() {
        populateService.populateAll();
        return ResponseEntity.ok("Base de datos poblada correctamente con datos de prueba.");
    }
}