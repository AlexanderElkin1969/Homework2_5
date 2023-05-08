package pro.sky.java.course2.Employee.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectNameExeption extends RuntimeException {
    public IncorrectNameExeption(String message) {
        super(message);
    }
}
