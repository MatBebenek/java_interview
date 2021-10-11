package pl.irdis.interview.employee;

import pl.irdis.interview.BaseModel;
import pl.irdis.interview.permissions.BasePermission;
import pl.irdis.interview.permissions.EnergyPermission;
import pl.irdis.interview.permissions.TechnicalPermission;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee extends BaseModel {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechnicalPermission> techPermissions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnergyPermission> energyPermissions;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, List<TechnicalPermission> techPermissions, List<EnergyPermission> energyPermissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.techPermissions = techPermissions;
        this.energyPermissions = energyPermissions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<TechnicalPermission> getTechPermissions() {
        return techPermissions;
    }

    public void setTechPermissions(List<TechnicalPermission> techPermissions) {
        this.techPermissions = techPermissions;
    }

    public List<EnergyPermission> getEnergyPermissions() {
        return energyPermissions;
    }

    public void setEnergyPermissions(List<EnergyPermission> energyPermissions) {
        this.energyPermissions = energyPermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(techPermissions, employee.techPermissions) && Objects.equals(energyPermissions, employee.energyPermissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, techPermissions, energyPermissions);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", techPermissions=" + techPermissions +
                ", energyPermissions=" + energyPermissions +
                '}';
    }
}
