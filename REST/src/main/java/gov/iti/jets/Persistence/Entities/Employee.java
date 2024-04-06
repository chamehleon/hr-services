package gov.iti.jets.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees", schema = "hr")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salary_id", nullable = false)
    private Salary salary;

    @Column(name = "commission_pct", precision = 5, scale = 2)
    private BigDecimal commissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "manager")
    private Department managedDepartment = new Department();

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<JobHistory> jobhistories = new LinkedHashSet<>();

    @Transient
    private String managerName;

    @Transient
    private String departmentName;

    @Transient
    private String jobTitle;

    @Transient
    private BigDecimal salaryAmount;

    @PostLoad
    public void postLoad() {
        if (manager != null) {
            managerName = manager.getFirstName() + " " + manager.getLastName();
        }
        else {
            managerName = "N/A";
        }
        if (department != null) {
            departmentName = department.getDepartmentName();
        }
        else {
            departmentName = "N/A";
        }
        if(job != null){
            jobTitle = job.getJobTitle();
        }
        else {
            jobTitle = "N/A";
        }
        if(salary != null){
            salaryAmount = salary.getPaymentAmount();
        }
        else {
            salaryAmount = BigDecimal.ZERO;
        }
    }

}