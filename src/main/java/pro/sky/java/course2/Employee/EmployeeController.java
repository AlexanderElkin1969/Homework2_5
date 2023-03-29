package pro.sky.java.course2.Employee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServise employeeService;

    public EmployeeController(EmployeeServise employeeService) throws EmployeeNotFoundException{
        this.employeeService = employeeService;
    }
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String hello(){
        return "<h1 style = \" color: red \">Добро пожаловать. Введите запрос.</h1>";
    }

    @GetMapping(path="/find")
    public String find(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName){
        try {
            if (employeeService.findService(firstName, lastName)) {
                return firstName + " " + lastName + " в списке сотрудников.";
            }
        }catch (EmployeeNotFoundException e){
            return e.getLocalizedMessage();
        }
        return firstName + " " + lastName;
    }
    @GetMapping(path="/add")
    public String add(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName){
        try {
            if (employeeService.addService(firstName, lastName)) {
                return firstName + " " + lastName + " добавлен в список сотрудников.";
            }
        }catch (EmployeeAlreadyAdded e){
            return e.getLocalizedMessage();
        }catch (EmployeeStorageIsFullException e) {
            return e.getLocalizedMessage();
        }
        return firstName + " " + lastName;
    }

}
