package com.kando.util;

/**
 *
 */
public class UserNotExsistException extends RuntimeException {
    UserNotExsistException(){

    }

    public UserNotExsistException(String message) {
        super(message);
    }
}
