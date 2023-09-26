package com.sds.buysell.service;

import com.sds.buysell.model.LoginResponse;
import com.sds.buysell.payload.LoginRequest;

public interface KeyCloakService {

    LoginResponse login(LoginRequest payload);
}
