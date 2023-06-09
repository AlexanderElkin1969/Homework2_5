package pro.sky.java.course2.Employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectNameException extends RuntimeException {
    public IncorrectNameException(String message) {
        super(message);
    }
}
