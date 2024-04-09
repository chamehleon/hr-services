package gov.iti.jets.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments", schema = "hr")
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    // TODO not null or not?
//    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private Employee manager;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new LinkedHashSet<>();

    @Transient
    private Integer managerId;

    @Transient
    private Integer locationId;


    @PostLoad
    private void postLoad() {
        if (manager != null) {
            managerId = manager.getId();
        }else{
            managerId = null;

        }
        if (location != null) {
            locationId = location.getId();
        }
        else {
            locationId = null;
        }
    }

//    @OneToMany(mappedBy = "department")
//    private Set<JobHistory> jobhistories = new LinkedHashSet<>();

}