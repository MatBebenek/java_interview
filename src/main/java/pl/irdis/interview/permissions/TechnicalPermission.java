package pl.irdis.interview.permissions;

import pl.irdis.interview.employee.Employee;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "technical")
public class TechnicalPermission extends BasePermission {

    @Column(name = "name", nullable = false)
    private String name;

    public TechnicalPermission() {
    }

    public TechnicalPermission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalPermission that = (TechnicalPermission) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TechnicalPermission{" +
                "name='" + name + '\'' +
                '}';
    }
}
