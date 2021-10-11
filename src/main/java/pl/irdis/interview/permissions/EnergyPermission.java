package pl.irdis.interview.permissions;

import pl.irdis.interview.employee.Employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "energy")
public class EnergyPermission extends BasePermission {

    @Column(name = "validityDate", nullable = false)
    private LocalDate validityDate;

    public EnergyPermission() {
    }

    public EnergyPermission(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergyPermission that = (EnergyPermission) o;
        return Objects.equals(validityDate, that.validityDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validityDate);
    }

    @Override
    public String toString() {
        return "EnergyPermission{" +
                "validityDate=" + validityDate +
                '}';
    }
}
