package model;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idDepartment;
    private String name;
    private boolean status ;
    @OneToMany(mappedBy = "departmentId" , fetch = FetchType.LAZY )
    private List<Worker>workers;


    @Override
    public String toString() {
        return "Department{" +
                "idDepartment=" + idDepartment +
                ", name='" + name + '\'' +
                ", status=" + status + '}';
    }

    public Department(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Department that = (Department) object;
        return idDepartment == that.idDepartment &&
                status == that.status &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepartment, name, status);
    }
}
