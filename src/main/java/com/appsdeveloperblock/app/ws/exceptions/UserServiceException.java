package com.appsdeveloperblock.app.ws.exceptions;

public class UserServiceException extends RuntimeException {


    private static final long serialVersionUID = 3855504104546806684L;

    public UserServiceException(String message)
    {
        super(message);
    }
}
