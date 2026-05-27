package com.spotter_proyect.spotter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class KeyGenerator {
    public static void main(String[] args) {
        // 1. Genera una clave segura aleatoria específica para HS256
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // 2. Codifícala en Base64 para que puedas guardarla como texto
        String secretString = Encoders.BASE64.encode(key.getEncoded());

        // 3. Imprímela
        System.out.println("--- COPIA ESTA CLAVE EN TU PROPERTIES ---");
        System.out.println(secretString);
        System.out.println("-----------------------------------------");
    }
}