package pro.sky.java.course2.Employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.Employee.exception.DepartmentNotFoundException;
import pro.sky.java.course2.Employee.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public int maxSalary(int department) {
        ArrayList<Employee> employees = allOfDepartment(department);
        int size = employees.size();
        if (size == 0){
            throw new DepartmentNotFoundException("В " + department + " отделе нет сотрудников.");
        }else {
            return employees.get(size - 1).getSalary();
        }
    }

    @Override
    public int minSalary(int department) {
        ArrayList<Employee> employees = allOfDepartment(department);
        if (employees.size() == 0){
            throw new DepartmentNotFoundException("В " + department + " отделе нет сотрудников.");
        }else {
            return employees.get(0).getSalary();
        }
    }

    @Override
    public int sumSalary(int department) {
        int sum = 0;
        ArrayList<Employee> employees = allOfDepartment(department);
        if (employees.size() == 0) throw new DepartmentNotFoundException("В " + department + " отделе нет сотрудников.");
        for (Employee e : employees) {
            sum = sum + e.getSalary();
        }
        return sum;
    }

    @Override
    public ArrayList<Employee> allOfDepartment(int dep) {
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == dep )
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Map<Integer, ArrayList<Employee>> allSortedToDepartment() {
        Map<Integer,ArrayList<Employee>> sortedToDepartment = new HashMap<>();
        for (int i = 0; i < 3; i++) {                               //     3 департамента
            sortedToDepartment.put( i+1, allOfDepartment(i+1));
        }
        return sortedToDepartment;
    }
}
