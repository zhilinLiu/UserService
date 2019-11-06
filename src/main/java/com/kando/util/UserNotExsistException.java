package com.kando.util;


import org.apache.shiro.authc.AuthenticationException;

/**
 *
 */
public class UserNotExsistException extends AuthenticationException {
    UserNotExsistException(){

    }

    public UserNotExsistException(String message) {
        super(message);
    }
}
