package pro.sky.java.course2.Employee.Exeption;

public class EmployeeAlreadyAdded extends RuntimeException {

    public EmployeeAlreadyAdded(String message) {
        super(message);
    }

}
