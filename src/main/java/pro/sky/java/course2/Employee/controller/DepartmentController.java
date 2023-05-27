package pro.sky.java.course2.Employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.Employee.model.Employee;
import pro.sky.java.course2.Employee.service.DepartmentService;

import java.util.ArrayList;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/max")
    public String maxSalary(@PathVariable int id) {
        return departmentService.maxSalary(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public String minSalary(@PathVariable int id) {
        return departmentService.minSalary(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public String sumSalary(@PathVariable int id) {
        return departmentService.sumSalary(id);
    }


    @GetMapping(path = "/{id}/employees")
    public ArrayList<Employee> allOfDepartment(@PathVariable int id) {
        return departmentService.allOfDepartment(id);
    }

    @GetMapping(path = "/employees")
    public ArrayList<Employee> allSortedToDepartment() {
        return departmentService.allSortedToDepartment();
    }
}