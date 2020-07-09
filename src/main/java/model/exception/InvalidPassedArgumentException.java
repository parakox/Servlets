package model.exception;


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
