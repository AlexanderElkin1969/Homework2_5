package pro.sky.java.course2.Employee.Exeption;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }

}
