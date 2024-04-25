package com.les.exception;

/**
 * @author Joetao
 * @date 2023/11/29
 */
public class LoginFailedException extends BaseException{
    private String username;
    public LoginFailedException(String message, String username) {
        super(message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
