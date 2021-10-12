package pl.irdis.interview.employee;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDAO extends JpaRepository<Employee,Long> {

    Employee findByFirstName(String firstName);
    Employee findByLastName(String lastName);
    List<Employee> findByEnergyPermissionsNotEmpty();
    List<Employee> findByTechPermissionsNotEmpty();
}
