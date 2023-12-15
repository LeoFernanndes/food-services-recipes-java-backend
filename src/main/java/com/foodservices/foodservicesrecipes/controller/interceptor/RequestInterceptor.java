package com.foodservices.foodservicesrecipes.controller.interceptor;

import com.foodservices.foodservicesrecipes.dataClass.DecodedJwtDataClass;
import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import com.foodservices.foodservicesrecipes.service.AuthTokenService;
import com.foodservices.foodservicesrecipes.useCase.ValidateJwtAuthTokenUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Component
@PropertySource("classpath:application.properties")
public class RequestInterceptor implements HandlerInterceptor {

    private ValidateJwtAuthTokenUseCase validateJwtAuthTokenUseCase;
    public RequestInterceptor(ValidateJwtAuthTokenUseCase validateJwtAuthTokenUseCase){
        this.validateJwtAuthTokenUseCase = validateJwtAuthTokenUseCase;
    }

    // TODO: Find  way of sending messages in response along with error codes
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
        System.out.println("1 - pre handle.");
        System.out.println("METHOD type:" + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Servlet PATH: " + request.getServletPath());
        //check which controller method is requested
        if(object instanceof HandlerMethod){
            //can be added different logics
            Class<?> controllerClass = ((HandlerMethod) object).getBeanType();
            String methodName = ((HandlerMethod) object).getMethod().getName();
            System.out.println("Controller name: " + controllerClass.getName());
            System.out.println("Method name:" + methodName);
            String authorizationHeaderContent = request.getHeader("authorization");
            System.out.println(request.getHeader("authorization"));

            if (authorizationHeaderContent == null){
                System.out.println("Missing bearer token in Authorization header");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing bearer token in Authorization header");
                return false;
            } else {
                // TODO: Give a better handle to token split
                JwtDataClass jwtDataClass = new JwtDataClass();
                String[] tokenSubstrings = authorizationHeaderContent.split("Bearer ");
                String token = tokenSubstrings[1];
                jwtDataClass.setToken(token);
                DecodedJwtDataClass decodedJwtDataClass = validateJwtAuthTokenUseCase.execute(jwtDataClass);
                if(decodedJwtDataClass == null){
                    System.out.println("Invalid bearer token in Authentication header");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid bearer token in Authentication header");
                    return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model){
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception){
    }
}