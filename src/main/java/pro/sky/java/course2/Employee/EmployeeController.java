package pro.sky.java.course2.Employee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServise employeeService;

    public EmployeeController(EmployeeServise employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String hello(){
        return "<h1 style = \" color: red \">Добро пожаловать. Введите запрос.</h1>";
    }

}
