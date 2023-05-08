package pro.sky.java.course2.Employee.exeptions;

public class EmployeeAlreadyAdded extends RuntimeException {

    public EmployeeAlreadyAdded(String message) {
        super(message);
    }

}
