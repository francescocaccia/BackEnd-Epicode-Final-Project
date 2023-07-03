package main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteAlreadyPresentException extends RuntimeException {

    public ClienteAlreadyPresentException() {
        super("Gia esiste un cliente con la stessa mail");
    }
}
