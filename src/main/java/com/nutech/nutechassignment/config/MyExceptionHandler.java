package com.nutech.nutechassignment.config;

import com.nutech.nutechassignment.exception.*;
import com.nutech.nutechassignment.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    // authentication related exception
    @ExceptionHandler({BadCredentialsException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleBadCredentialsException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(103);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleUsernameNotFoundException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(103);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleAuthException() {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(108);
        response.setMessage("Token is not valid or expired");
        response.setData(null);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleAccessDeniedException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(104);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // Users
    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleUserNotFoundException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(105);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleUserAlreadyExistsException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(106);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({ImageFormatException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleFormatImageException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(102);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({NotEnoughBalanceException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleNotEnoughBalanceException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(103);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // service and transaction
    @ExceptionHandler({ServiceNotFoundException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleServiceNotFoundException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(105);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({TransactionHistoryException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleTransactionHistoryException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(102);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // jakarta validation
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<WebResponse<Object>> handleConstraintViolationException(Exception e) {

        WebResponse<Object> response = new WebResponse<>();
        response.setStatus(102);
        response.setMessage(e.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
