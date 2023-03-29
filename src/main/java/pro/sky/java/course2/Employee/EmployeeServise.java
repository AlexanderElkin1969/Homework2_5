package pro.sky.java.course2.Employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServise {
 //   private static Employee[] employees = new Employee[10];
    public static Employee[] employees = {
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

    public Employee findService(String firstName, String lastName) throws EmployeeNotFoundException{
        if (employees.length == 0){
            throw  new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null){
                continue;
            }
            if (firstName.equals(employees[i].getFirstName())&&lastName.equals(employees[i].getLastName())){
                return employees[i];
            }
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " в списке сотрудников отсутствует.");
    }

    public Employee addService(String firstName, String lastName) throws EmployeeAlreadyAdded, EmployeeStorageIsFullException {
        int freeElement = -1;
        try {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null){
                    freeElement = i;
                    break;
                }
            }
            if (freeElement == -1){
                throw  new EmployeeStorageIsFullException("Список сотрудников переполнен. Невозможно добавить нового сотрудника.");
            }
            if (findService(firstName, lastName) != null) {
                throw  new EmployeeAlreadyAdded(firstName + " " + lastName + " уже есть в списке сотрудников.");
            }
        }catch (EmployeeNotFoundException e){
            employees[freeElement] = new Employee(firstName, lastName);
                return employees[freeElement];
        }
        return employees[0];
    }

    public Employee removeService(String firstName, String lastName) throws EmployeeNotFoundException{
        Employee buffer = new Employee(firstName, lastName);
        if (employees.length == 0){
            throw  new EmployeeNotFoundException("Список сотрудников пуст.");
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null){
                continue;
            }
            if (buffer.equals(employees[i])){
                employees[i] = null;
                return buffer;
            }
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " в списке сотрудников отсутствует.");
    }

}
