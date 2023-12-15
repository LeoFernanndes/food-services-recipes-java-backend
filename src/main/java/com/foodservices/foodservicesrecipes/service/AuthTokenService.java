package com.foodservices.foodservicesrecipes.service;

import com.foodservices.foodservicesrecipes.controller.interceptor.RequestInterceptor;
import com.foodservices.foodservicesrecipes.dataClass.DecodedJwtDataClass;
import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenService.class);

    public DecodedJwtDataClass decodeJwtDataClass(JwtDataClass jwtDataClass) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JwtDataClass> request = new HttpEntity<>(jwtDataClass, headers);

        logger.info("Validation service url " + jwtValidationUrl);

        try {
            ResponseEntity<DecodedJwtDataClass> validatedTokenData = restTemplate.exchange(jwtValidationUrl, HttpMethod.POST, request, DecodedJwtDataClass.class);
            DecodedJwtDataClass tokenData = validatedTokenData.getBody();
            logger.info("Ok");
            return tokenData;
        } catch (HttpClientErrorException.BadRequest error) {
            logger.warn("Bad request " + error.getMessage());
            return null;
        } catch (HttpServerErrorException.InternalServerError error) {
            logger.warn("Internal server error " + error.getMessage());
            return null;
        } catch (Exception error){
            logger.warn("Unknow exception " + error.getMessage());
            return null;
        }
    }
}