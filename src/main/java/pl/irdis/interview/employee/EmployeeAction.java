package pl.irdis.interview.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.irdis.interview.exception.ResourceNotFoundException;
import pl.irdis.interview.permissions.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeAction {

    private static final Logger LOGGER = Logger.getLogger(EmployeeAction.class.getName());

    @Autowired
    EmployeeDAO employeeDAO;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        LOGGER.info("Receiving all employees");
        return employeeDAO.findAll();
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(Long id) {
        LOGGER.info("Receiving employee with id=" + id);
        return employeeDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @GetMapping(value = "", params = {"firstName"})
    public Employee getEmployeeByFirstName(String firstName) {
        LOGGER.info("Receiving employee with firstName=" + firstName);
        return employeeDAO.findByFirstName(firstName);
    }

    @GetMapping(value = "/energy")
    public List<Employee> getAllEmployeesWithEnergyPermissions() {
        LOGGER.info("Receiving employees with energy permissions.");
        return employeeDAO.findByEnergyPermissionsNotEmpty();
    }

    @GetMapping(value = "/tech")
    public List<Employee> getAllEmployeesWithTechnicalPermissions() {
        LOGGER.info("Receiving employees with technical permissions.");
        return employeeDAO.findByTechPermissionsNotEmpty();
    }

    @GetMapping(value = "", params = {"lastName"})
    public Employee getEmployeeByLastName(String lastName) {
        LOGGER.info("Receiving employee with lastName=" + lastName);
        return employeeDAO.findByLastName(lastName);
    }

    @PostMapping(value = "")
    public Employee addEmployee(@RequestBody Employee employee) {
        LOGGER.info("Adding new employee: " + employee);
        return employeeDAO.save(employee);
    }

    @DeleteMapping("/{id}/delete")
    public Employee deleteEmployee(@PathVariable Long id) {
        LOGGER.info("Deleting employee for id: " + id);
        return employeeDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @PutMapping("/addTechPerm/{id}")
    public Employee addTechnicalPermissions(@PathVariable Long id, @RequestBody TechnicalPermission technicalPermission) {
        LOGGER.info("Adding new technical permission: " + technicalPermission + " for employee with id=" + id);
        Employee employee = employeeDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.getTechPermissions().add(technicalPermission);
        return employeeDAO.save(employee);
    }

    @PutMapping("/addEnergyPerm/{id}")
    public Employee addEnergyPermissions(@PathVariable Long id, @RequestBody EnergyPermission energyPermission) {
        LOGGER.info("Adding new energy permission: " + energyPermission + " for employee with id=" + id);
        Employee employee = employeeDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.getEnergyPermissions().add(energyPermission);
        return employeeDAO.save(employee);
    }

    @PutMapping("/{id}/removeEnergyPerm/{number}")
    public Employee removeEnergyPermissions(@PathVariable Long id, @PathVariable(value="number") Long number) {
        LOGGER.info("Removing energy permission with number: " + number + " for employee with id=" + id);
        Employee employee = employeeDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.getEnergyPermissions().removeIf(x->x.getNumber().equals(number));
        return employeeDAO.save(employee);
    }

    @PutMapping("/{id}/removeTechPerm/{number}")
    public Employee removeTechnicalPermissions(@PathVariable Long id, @PathVariable Long number) {
        LOGGER.info("Removing technical permission with number: " + number + " for employee with id=" + id);
        Employee employee = employeeDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.getTechPermissions().removeIf(x->x.getNumber().equals(number));
        return employeeDAO.save(employee);
    }

}
