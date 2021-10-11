package pl.irdis.interview.permissions;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BasePermission implements Serializable {

    @Id
    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    public Long getNumber() {
        return number;
    }
}
