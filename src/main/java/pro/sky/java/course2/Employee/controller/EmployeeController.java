package pro.sky.java.course2.Employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.Employee.exception.*;
import pro.sky.java.course2.Employee.model.Employee;
import pro.sky.java.course2.Employee.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String hello() {
        return "<h1 style = \" color: red \">Добро пожаловать. Введите запрос.</h1>";
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName) {
            return employeeService.find(firstName, lastName);
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam("firstName") String firstName,
                      @RequestParam("lastName") String lastName) {
            return employeeService.add(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {
            return employeeService.remove(firstName, lastName);
    }

    @GetMapping(path = "/getList")
    public Map<String, Employee> getList() {
        return employeeService.getEmployees();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String allMyException(RuntimeException e){
        return e.getMessage();
    }

}
