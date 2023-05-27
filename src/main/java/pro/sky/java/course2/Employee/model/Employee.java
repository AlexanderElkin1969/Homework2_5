package pro.sky.java.course2.Employee.model;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int department;
    private int salary;


    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = 1 + (int) (Math.random() * 3);            //    3 департамента
        this.salary = 100_000 + (int) (Math.random() * 100_001);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "  ФИО: " + getFullName() +
                "  Отдел: " + department +
                "  Зарплата: " + salary;
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        return this.getFullName().equals(((Employee) other).getFullName());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(department);
    }

}
