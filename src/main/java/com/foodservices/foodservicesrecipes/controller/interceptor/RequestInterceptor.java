package com.foodservices.foodservicesrecipes.controller.interceptor;

import com.foodservices.foodservicesrecipes.dataClass.DecodedJwtDataClass;
import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import com.foodservices.foodservicesrecipes.useCase.ValidateJwtAuthTokenUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Component
@PropertySource("classpath:application.properties")
public class RequestInterceptor implements HandlerInterceptor {

//    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
//    private ValidateJwtAuthTokenUseCase validateJwtAuthTokenUseCase;
//    public RequestInterceptor(ValidateJwtAuthTokenUseCase validateJwtAuthTokenUseCase){
//        this.validateJwtAuthTokenUseCase = validateJwtAuthTokenUseCase;
//    }
//
//    // TODO: Find  way of sending messages in response along with error codes
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
//        logger.info("Pre handler");
//        logger.info("METHOD type:" + request.getMethod());
//
//        //check which controller method is requested
//        if(object instanceof HandlerMethod){
//            //can be added different logics
//            Class<?> controllerClass = ((HandlerMethod) object).getBeanType();
//            String methodName = ((HandlerMethod) object).getMethod().getName();
//            logger.info("Controller name: " + controllerClass.getName());
//            logger.info("Method name: " + methodName);
//            String authorizationHeaderContent = request.getHeader("authorization");
//            logger.debug(request.getHeader("authorization"));
//            if (authorizationHeaderContent == null){
//                logger.info("Missing bearer token in Authorization header");
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing bearer token in Authorization header");
//                return false;
//            } else {
//                // TODO: Give a better handle to token split
//                JwtDataClass jwtDataClass = new JwtDataClass();
//                String[] tokenSubstrings = authorizationHeaderContent.split("Bearer ");
//                String token = tokenSubstrings[1];
//                jwtDataClass.setToken(token);
//                DecodedJwtDataClass decodedJwtDataClass = validateJwtAuthTokenUseCase.execute(jwtDataClass);
//                if(decodedJwtDataClass == null){
//                    logger.info("Invalid bearer token in Authentication header");
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid bearer token in Authentication header");
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//        }
//        logger.info("Unknown handler method");
//        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model){
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception){
//    }
}