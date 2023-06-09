package pro.sky.java.course2.Employee.service;


import pro.sky.java.course2.Employee.model.Employee;

import java.util.ArrayList;

public interface DepartmentService {

    public ArrayList<Employee> allOfDepartment(int department);

    public ArrayList<Employee> allSortedToDepartment();

    String maxSalary(int department);

    String minSalary(int department);
}
