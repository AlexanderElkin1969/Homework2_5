package pro.sky.java.course2.Employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.Employee.exception.DepartmentNotFoundException;
import pro.sky.java.course2.Employee.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    private Map<String, Employee> employees;

    @BeforeEach
    public void beforeEach(){
        employees = new HashMap<>(Map.of(
                "Александров Александр",
                new Employee( "Александр", "Александров", 1, 100_000),
                "Александров Борис",
                new Employee( "Борис", "Александров", 1, 150_000),
                "Борисов Александр",
                new Employee( "Александр", "Борисов", 2, 200_000),
                "Александров Иван",
                new Employee( "Иван", "Александров", 3, 120_000),
                "Борисов Борис",
                new Employee( "Борис", "Борисов", 3, 130_000)
        ));
        when(employeeService.getEmployees()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("maxSalaryOfDepartmentTestParams")
    public void maxSalaryOfDepartmentTest(int department, int expected) {
        Assertions.assertEquals(out.maxSalary(department), expected);
    }


    @ParameterizedTest
    @MethodSource("minSalaryOfDepartmentTestParams")
    public void minSalaryOfDepartmentTest(int department, int expected) {
        Assertions.assertEquals(out.minSalary(department), expected);
    }

    @ParameterizedTest
    @MethodSource("sumAllSalaryOfDepartmentTestParams")
    public void sumAllSalaryOfDepartmentTest(int department, int expected) {
        Assertions.assertEquals(out.sumSalary(department), expected);
    }

    @Test
    public void shouldThrowDepartmentNotFoundException() {
        Assertions.assertThrows(DepartmentNotFoundException.class,
                () -> out.maxSalary(0));Assertions.assertThrows(DepartmentNotFoundException.class,
                () -> out.minSalary(0));Assertions.assertThrows(DepartmentNotFoundException.class,
                () -> out.sumSalary(0));
    }

    public static Stream<Arguments> maxSalaryOfDepartmentTestParams() {
        return  Stream.of(
                Arguments.of( 1, 150_000),
                Arguments.of( 2, 200_000),
                Arguments.of( 3, 130_000)
        );
    }

    public static Stream<Arguments> minSalaryOfDepartmentTestParams() {
            return  Stream.of(
                    Arguments.of( 1, 100_000),
                    Arguments.of( 2, 200_000),
                    Arguments.of( 3, 120_000)
            );

    }

    public static Stream<Arguments> sumAllSalaryOfDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 250_000),
                Arguments.of(2, 200_000),
                Arguments.of(3, 250_000)
        );
    }

}
