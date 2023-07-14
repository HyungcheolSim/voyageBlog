package com.sparta.voyageblog.filter;

import com.sparta.voyageblog.dto.ErrorResponse;
import com.sparta.voyageblog.exception.TokenNotValidateException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "ExceptionHandlerFilter")
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenNotValidateException e) {
            log.error("TokenNotValidateException : " + e.getLocalizedMessage());
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e.getLocalizedMessage());
        }catch(UnsupportedOperationException ex){
            log.error("UnsupportedOperationException : " + ex.getLocalizedMessage());
            setErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, response, ex.getLocalizedMessage());
        }catch(RuntimeException e){
            log.error("RuntimeException : " + e.getLocalizedMessage());
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e.getLocalizedMessage());
        }catch(IOException e){
            log.error("IOException : " + e.getLocalizedMessage());
            setErrorResponse(HttpStatus.FORBIDDEN, response, e.getLocalizedMessage());
        }
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");
        ErrorResponse errorResponse = new ErrorResponse(status,message);
        response.getWriter().write(errorResponse.convertToJson());
    }
}
