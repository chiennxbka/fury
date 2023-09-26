package com.sds.fury.controller;

import com.sds.fury.model.LoginResponse;
import com.sds.fury.payload.LoginRequest;
import com.sds.fury.service.KeyCloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenController {

    private final KeyCloakService keyCloakService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest payload) {
        var result = keyCloakService.login(payload);
        return ResponseEntity.ok(result);
    }
}
