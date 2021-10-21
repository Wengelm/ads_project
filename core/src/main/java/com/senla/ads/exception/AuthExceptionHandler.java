package com.senla.ads.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthExceptionHandler.class);

    @Override
    @ExceptionHandler(value = {HttpClientErrorException.Unauthorized.class})
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage("Unauthorized");
        httpServletResponse.getWriter().write(convertObjectToJson(apiErrorMessage));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AccessDeniedException accessDeniedException) throws IOException {
        logger.error("AccessDenied error: {}", accessDeniedException.getMessage());
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage("Access Denied");
        httpServletResponse.getWriter().write(convertObjectToJson(apiErrorMessage));
    }


    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
