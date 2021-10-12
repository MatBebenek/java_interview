package pl.irdis.interview.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.irdis.interview.exception.ResourceNotFoundException;
import pl.irdis.interview.permissions.EnergyPermission;
import pl.irdis.interview.permissions.TechnicalPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class EmployeeActionTest {

    @Autowired
    private EmployeeAction employeeAction;

    @MockBean
    private EmployeeDAO employeeDAO;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        Employee employee = new Employee("Jan", "Kowalski");
        doReturn(Optional.of(employee)).when(employeeDAO).findById(1l);

        Employee returnedEmployee = employeeAction.getEmployeeById(1l);

        Assertions.assertNotNull(returnedEmployee,"The employee should not be null");
        Assertions.assertSame(returnedEmployee, employee, "The employee returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(employeeDAO).findById(1l);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeAction.getEmployeeById(1l);
        });
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        Employee employeeFirst = new Employee("Jan", "Kowalski");
        Employee employeeSecond = new Employee("Marek", "Nowak");
        doReturn(Arrays.asList(employeeFirst, employeeSecond)).when(employeeDAO).findAll();

        List<Employee> employees = employeeAction.getAllEmployees();

        Assertions.assertEquals(2, employees.size(), "findAll should return 2 employees");
    }

    @Test
    @DisplayName("Test save employee")
    void testSave() {
        Employee employee = new Employee("Jan", "Kowalski");
        doReturn(employee).when(employeeDAO).save(employee);

        Employee returnedEmployee = employeeAction.addEmployee(employee);

        Assertions.assertNotNull(returnedEmployee, "The saved widget should not be null");
        Assertions.assertEquals(employee,returnedEmployee);
    }

}
