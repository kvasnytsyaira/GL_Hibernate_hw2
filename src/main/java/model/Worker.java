package model;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    @Enumerated(EnumType.STRING)
    private Availability availability;
    private String full_name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department departmentId;

    public Worker(int age, Availability availability, String full_name, Department departmentId) {
        this.age = age;
        this.availability = availability;
        this.full_name = full_name;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", age=" + age +
                ", availability=" + availability +
                ", full_name='" + full_name + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Worker worker = (Worker) object;
        return id == worker.id &&
                age == worker.age &&
                availability == worker.availability &&
                Objects.equals(full_name, worker.full_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, availability, full_name);
    }
}