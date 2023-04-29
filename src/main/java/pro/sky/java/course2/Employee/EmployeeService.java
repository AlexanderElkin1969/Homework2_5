package pro.sky.java.course2.Employee;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.Employee.Exeption.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    public static final int MAX_COUNT_EMPLOYEES = 10;
    private final static List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Александр", "Александров"),
            new Employee("Александр", "Борисов"),
            new Employee("Владимир", "Борисов"),
            new Employee("Александр", "Иванов"),
            new Employee("Иван", "Иванов"),
            new Employee("Александр", "Егоров"),
            new Employee("Александр", "Петров")
    ));

    public Employee findService(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee buffer = checkCorrectName(firstName, lastName);
        if (employees.size() == 0) {
            throw new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        for (Employee employee : employees) {
            if (employee.equals(buffer)) {
                return buffer;
            }
        }
        throw new EmployeeNotFoundException(buffer + " в списке сотрудников отсутствует.");

    }

    public Employee addService(String firstName, String lastName) throws EmployeeAlreadyAdded, EmployeeStorageIsFullException {
        Employee buffer = checkCorrectName(firstName, lastName);
        if (employees.size() == MAX_COUNT_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Список сотрудников переполнен. Число сотрудников по штату не может превышать 10 человек.");
        }
        if (employees.contains(buffer)) {
            throw new EmployeeAlreadyAdded(buffer + " уже есть в списке сотрудников.");
        }
        employees.add(buffer);
        return buffer;
    }

    public Employee removeService(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee buffer = checkCorrectName(firstName, lastName);
        if (employees.size() == 0) {
            throw new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        if (!employees.contains(buffer)) {
            throw new EmployeeNotFoundException(buffer + " в списке сотрудников отсутствует.");
        }
        employees.remove(buffer);
        return buffer;
    }

    public Employee checkCorrectName(String firstName, String lastName) throws IncorrectNameExeption {
        if (StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName)) {
            String name = StringUtils.capitalize(firstName.toLowerCase());
            String surname = StringUtils.capitalize(lastName.toLowerCase());
            return new Employee(name, surname);
        } else {
            throw new IncorrectNameExeption("Имя, отчество и фамилия должны содержать только буквы.");
        }
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }
}
