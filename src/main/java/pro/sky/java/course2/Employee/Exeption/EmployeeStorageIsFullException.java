package pro.sky.java.course2.Employee.Exeption;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }

}
