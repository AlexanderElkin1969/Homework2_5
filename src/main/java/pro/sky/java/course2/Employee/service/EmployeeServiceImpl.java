package pro.sky.java.course2.Employee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.Employee.exception.IncorrectNameException;
import pro.sky.java.course2.Employee.model.Employee;
import pro.sky.java.course2.Employee.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.Employee.exception.EmployeeNotFoundException;
import pro.sky.java.course2.Employee.exception.EmployeeStorageIsFullException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final int MAX_COUNT_EMPLOYEES = 12;           // по штату 10 сотрудников в 3 отделах
    private static Map<String, Employee> employees = new HashMap<>(Map.of(
            "Александров Александр", new Employee("Александр", "Александров"),
            "Александров Борис", new Employee("Борис", "Александров"),
            "Борисов Александр", new Employee("Александр", "Борисов"),
            "Александров Иван", new Employee("Иван", "Александров"),
            "Борисов Борис", new Employee("Борис", "Борисов"),
            "Иванов Борис", new Employee("Борис", "Иванов"),
            "Иванов Александр", new Employee("Александр", "Иванов"),
            "Борисов Иван", new Employee("Иван", "Борисов"),
            "Иванов Иван", new Employee("Иван", "Иванов")
    ));

    @Override
    public Employee find(String name, String surname) throws EmployeeNotFoundException {
        String str = checkCorrectName(surname) + " " + checkCorrectName(name);
        if (employees.size() == 0) {
            throw new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        if (employees.containsKey(str)) {
            return employees.get(str);
        } else {
            throw new EmployeeNotFoundException(str + " в списке сотрудников отсутствует.");
        }
    }

    @Override
    public Employee add(String name, String surname) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        String correctName = checkCorrectName(name);
        String correctSurname = checkCorrectName(surname);
        String str = correctSurname + " " + correctName;
        if (employees.size() == MAX_COUNT_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Список сотрудников переполнен. Число сотрудников по штату не может превышать " +
                    MAX_COUNT_EMPLOYEES + " человек.");
        }
        if (employees.containsKey(str)) {
            throw new EmployeeAlreadyAddedException(str + " уже есть в списке сотрудников.");
        }
        Employee buffer = new Employee( correctName, correctSurname);
        employees.put(str, buffer);
        return buffer;
    }

    @Override
    public Employee remove(String name, String surname) throws EmployeeNotFoundException {
        Employee buffer;
        String str = checkCorrectName(surname) + " " + checkCorrectName(name);
        if (employees.size() == 0) {
            throw new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        if (employees.containsKey(str)) {
            buffer = employees.get(str);
            employees.remove(str);
            return buffer;
        } else {
            throw new EmployeeNotFoundException(str + " в списке сотрудников отсутствует.");
        }
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return Map.copyOf(employees);
    }

    public String checkCorrectName(String name) throws IncorrectNameException {
        if (StringUtils.isAlpha(name)) {
            return StringUtils.capitalize(name.toLowerCase());
        } else {
            throw new IncorrectNameException("Имя или фамилия должны содержать только буквы.");
        }
    }
}
