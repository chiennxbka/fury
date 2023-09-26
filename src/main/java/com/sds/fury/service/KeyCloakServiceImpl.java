package com.sds.fury.service;

import com.sds.fury.model.LoginResponse;
import com.sds.fury.payload.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class KeyCloakServiceImpl implements KeyCloakService {

    private final WebClient keyCloakWebClient;

    @Value("${spring.security.keycloak.grant.type}")
    private String grantType;

    @Value("${spring.security.keycloak.client.id}")
    private String clientId;

    /**
     * @param payload
     * @return
     */
    @Override
    public LoginResponse login(LoginRequest payload) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("grant_type", grantType);
        bodyValues.add("client_id", clientId);
        bodyValues.add("username", payload.getUsername());
        bodyValues.add("password", payload.getPassword());
        try {
            return keyCloakWebClient.post().uri("")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromFormData(bodyValues))
                    .retrieve()
                    .bodyToMono(LoginResponse.class).block();

        } catch (WebClientResponseException exception) {
            throw new BadCredentialsException(exception.getMessage());
        }
    }
}
