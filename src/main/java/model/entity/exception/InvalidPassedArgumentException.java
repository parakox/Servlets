package model.entity.exception;

import model.entity.entity.Message;

public class InvalidPassedArgumentException extends Exception {

    private String errorMessage;
    public InvalidPassedArgumentException(String errorMessage){
        this.errorMessage=errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
