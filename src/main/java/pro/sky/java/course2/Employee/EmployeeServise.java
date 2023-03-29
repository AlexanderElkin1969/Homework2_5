package pro.sky.java.course2.Employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServise {
 //   private static Employee[] employees = new Employee[10];
    private static Employee[] employees = {
            new Employee("Александр", "Александров"),
            new Employee("Александр", "Борисов"),
            new Employee("Владимир", "Борисов"),
            new Employee("Александр", "Иванов"),
            new Employee("Иван", "Иванов"),
            new Employee("Александр", "Егоров"),
            new Employee("Александр", "Петров"),
            null,
            null,
            null,
    };

    public boolean findService(String firstName, String lastName) throws EmployeeNotFoundException{
        if (employees.length == 0){
            throw  new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null){
                continue;
            }
            if (firstName.equals(employees[i].getFirstName())&&lastName.equals(employees[i].getLastName())){
                return true;
            }
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " в списке сотрудников отсутствует.");
    }
    public boolean addService(String firstName, String lastName) throws EmployeeAlreadyAdded, EmployeeStorageIsFullException {
        boolean b;
        try {
            b = findService(firstName, lastName);
        }catch (EmployeeNotFoundException e){
            b = false;
        }
        if (b){
            throw  new EmployeeAlreadyAdded(firstName + " " + lastName + " уже есть в списке сотрудников.");
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null){
                continue;
            }else {
                employees[i] = new Employee(firstName, lastName);
                return true;
            }
        }
        throw new EmployeeStorageIsFullException("Список сотрудников переполнен. Невозможно добавить нового сотрудника.");
    }

}
