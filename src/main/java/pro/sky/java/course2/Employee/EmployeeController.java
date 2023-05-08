package pro.sky.java.course2.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.Employee.exeptions.*;
import pro.sky.java.course2.Employee.model.Employee;

import java.util.List;

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
    public String find(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findService(firstName, lastName).toString() + " в списке сотрудников";
        } catch (EmployeeNotFoundException e) {
            return e.getLocalizedMessage();
        }
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("firstName") String firstName,
                      @RequestParam("lastName") String lastName) {
        try {
            return employeeService.addService(firstName, lastName).toString() + " добавлен в список сотрудников.";
        } catch (EmployeeAlreadyAdded | EmployeeStorageIsFullException e) {
            return e.getLocalizedMessage();
        }
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {
        try {
            return employeeService.removeService(firstName, lastName).toString() + " удален из списка сотрудников";
        } catch (EmployeeNotFoundException e) {
            return e.getLocalizedMessage();
        }
    }

    @GetMapping(path = "/getList")
    public List<Employee> getList() {
        return employeeService.getEmployees();
    }

    @ExceptionHandler(IncorrectNameExeption.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String IncorrectNameExeption(IncorrectNameExeption e){
        return e.getMessage();
    }

}
