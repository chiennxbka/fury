package com.sds.fury.service;

import com.sds.fury.model.LoginResponse;
import com.sds.fury.payload.LoginRequest;

public interface KeyCloakService {

    LoginResponse login(LoginRequest payload);
}
