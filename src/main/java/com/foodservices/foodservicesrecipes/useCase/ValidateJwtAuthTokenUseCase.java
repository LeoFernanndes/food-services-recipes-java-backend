package com.foodservices.foodservicesrecipes.useCase;

import com.foodservices.foodservicesrecipes.dataClass.DecodedJwtDataClass;
import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import com.foodservices.foodservicesrecipes.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ValidateJwtAuthTokenUseCase extends NotPersistenceBasedUseCase{

    private AuthTokenService authTokenService;

    public ValidateJwtAuthTokenUseCase(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    public DecodedJwtDataClass execute(JwtDataClass jwtDataClass) {
        return  authTokenService.decodeJwtDataClass(jwtDataClass);
    }
}