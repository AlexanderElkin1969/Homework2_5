package pro.sky.java.course2.Employee.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.Employee.exception.*;
import pro.sky.java.course2.Employee.model.Employee;

import static pro.sky.java.course2.Employee.service.EmployeeServiceImpl.MAX_COUNT_EMPLOYEES;

import java.util.Map;

public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @AfterEach
    public void afterEach() {
        String key, name, surname;
        for (Map.Entry<String, Employee> entry : out.getEmployees().entrySet()) {
            key = entry.getKey();
            if (key.contains("Abc")) {
                name = entry.getValue().getFirstName();
                surname = entry.getValue().getLastName();
                out.remove(name, surname);
            }
        }
    }

    @Test
    public void shouldWellWorkWhenValidInput() {
        int size = out.getEmployees().size();
        Employee result = out.add("Abc", "Abc");
        Assertions.assertEquals(out.find("Abc", "Abc"), result);
        Assertions.assertEquals(out.getEmployees().size(), ++size);
        Assertions.assertEquals(out.remove("Abc", "Abc"), result);
        Assertions.assertEquals(out.getEmployees().size(), --size);
    }

    @Test
    public void shouldThrowIncorrectNameExceptionWhenInvalidInput() {
        Assertions.assertThrows(IncorrectNameException.class,
                () -> out.find("Abc1", "Abc"));
        Assertions.assertThrows(IncorrectNameException.class,
                () -> out.find("Abc", "Abc+"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFind() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.find("Abc", "Abc"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemove() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.remove("Abc", "Abc"));
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenReAdd() {
        Employee result = out.add("Abc", "Abc");
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.add("Abc", "Abc"));
    }

    @Test
    public void shouldThrowEmployeeStorageIsFullExceptionWhenSizeExceededLimit() {
        int size = out.getEmployees().size();
        Employee buffer;
        for (int i = 0; i < MAX_COUNT_EMPLOYEES - size; i++) {
            buffer = out.add("Abc", "Ab" + ((char) ('c' + i  +1)));
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class,
                () -> out.add("Abc", "Abz"));
    }
}
