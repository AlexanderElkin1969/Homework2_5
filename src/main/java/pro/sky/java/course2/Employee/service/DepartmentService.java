package pro.sky.java.course2.Employee.service;


import pro.sky.java.course2.Employee.model.Employee;

import java.util.ArrayList;
import java.util.Map;

public interface DepartmentService {

    ArrayList<Employee> allOfDepartment(int department);

    Map<Integer, ArrayList<Employee>> allSortedToDepartment();

    int maxSalary(int department);

    int minSalary(int department);

    int sumSalary(int department);
}
