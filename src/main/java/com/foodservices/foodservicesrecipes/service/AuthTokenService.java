package com.foodservices.foodservicesrecipes.service;

import com.foodservices.foodservicesrecipes.dataClass.DecodedJwtDataClass;
import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthTokenService {
    @Value("${env.auth.authUrl}")
    private String jwtValidationUrl;

    public DecodedJwtDataClass decodeJwtDataClass(JwtDataClass jwtDataClass) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JwtDataClass> request = new HttpEntity<>(jwtDataClass, headers);

        try {
            ResponseEntity<DecodedJwtDataClass> validatedTokenData = restTemplate.exchange(jwtValidationUrl, HttpMethod.POST, request, DecodedJwtDataClass.class);
            return validatedTokenData.getBody();
        } catch (HttpClientErrorException.BadRequest error) {
            return null;
        } catch (HttpServerErrorException.InternalServerError error) {
            return null;
        } catch (Exception error){
            return null;
        }
    }
}