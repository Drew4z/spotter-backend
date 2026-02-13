package com.spotter_proyect.spotter.core.useCases;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo") // <--- Ojo a la ruta
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("🔒 ¡ACCESO CONCEDIDO! Si ves esto, tu Token es válido.");
    }
}