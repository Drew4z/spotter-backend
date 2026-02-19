package com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.adapter.in;

import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.in.LoginUseCase;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponse;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private final LoginUseCase login;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(login.login(request));
    }
}
