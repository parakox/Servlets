package model.exception;

import model.entity.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidPassedArgumentExceptionTest {

    @Test
    void getMessage() {
        try {
            throw new InvalidPassedArgumentException(Message.NAME_NOT_MATCHES_LENGTH.getMessage());
        } catch (InvalidPassedArgumentException e) {
            assertEquals(e.getMessage(),Message.NAME_NOT_MATCHES_LENGTH.getMessage());
        }
    }
}