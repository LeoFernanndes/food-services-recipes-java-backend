package com.foodservices.foodservicesrecipes.useCase;

import com.foodservices.foodservicesrecipes.dataClass.DecodedJwtDataClass;
import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import com.foodservices.foodservicesrecipes.service.AuthTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ValidateJwtAuthTokenUseCase extends NotPersistenceBasedUseCase{

    private AuthTokenService authTokenService;
    private static final Logger logger = LoggerFactory.getLogger(ValidateJwtAuthTokenUseCase.class);

    public ValidateJwtAuthTokenUseCase(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    public DecodedJwtDataClass execute(JwtDataClass jwtDataClass) {
        logger.info("validateJwtAuthTokenUseCase");
        return  authTokenService.decodeJwtDataClass(jwtDataClass);
    }
}