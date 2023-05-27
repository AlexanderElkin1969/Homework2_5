package pro.sky.java.course2.Employee.service;

import pro.sky.java.course2.Employee.model.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee find(String firstName, String lastName);

    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Map<String, Employee> getEmployees();

}
