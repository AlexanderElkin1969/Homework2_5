package pro.sky.java.course2.Employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.Employee.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String maxSalary(int department) {
        ArrayList<Employee> employees = allOfDepartment(department);
        int size = employees.size();
        if (size == 0) return "В " + department + " отделе нет сотрудников.";
        return "Максимальная зарплата в " + department + " отделе: " + employees.get(size - 1).toString();
    }

    @Override
    public String minSalary(int department) {
        ArrayList<Employee> employees = allOfDepartment(department);
        if (employees.size() == 0) return "В " + department + " отделе нет сотрудников.";
        return "Минимальная зарплата в " + department + " отделе: " + employees.get(0).toString();
    }

    @Override
    public ArrayList<Employee> allOfDepartment(int dep) {
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == dep )
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Employee> allSortedToDepartment() {
        return employeeService.getEmployees().values().stream()
                .sorted(Comparator.comparingInt(Employee::hashCode))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
