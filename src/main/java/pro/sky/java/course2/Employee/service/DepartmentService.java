package pro.sky.java.course2.Employee.service;


import pro.sky.java.course2.Employee.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> allOfDepartment(int department);

    Map<Integer, List<Employee>> allSortedToDepartment();

    int maxSalary(int department);

    int minSalary(int department);

    int sumSalary(int department);
}
