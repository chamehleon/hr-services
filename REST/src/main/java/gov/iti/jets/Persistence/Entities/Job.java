package gov.iti.jets.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jobs", schema = "hr")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @NotNull
    @Column(name = "min_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal minSalary;

    @NotNull
    @Column(name = "max_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxSalary;

    @OneToMany(mappedBy = "job")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<JobHistory> jobhistories = new LinkedHashSet<>();

}