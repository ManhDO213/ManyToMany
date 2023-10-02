package spring.tutorial.manytomany.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@Builder
@Setter
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   private String name;
   private String email;

   @ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
   @JoinTable(
           name = "employee_roles",
           joinColumns = @JoinColumn(
                   name = "employee_id", referencedColumnName = "id"
           ),
           inverseJoinColumns = @JoinColumn(
                   name = "role_id", referencedColumnName = "id"
           )
   )
   Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return (id == employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
