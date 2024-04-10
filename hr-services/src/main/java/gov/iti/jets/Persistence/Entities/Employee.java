package gov.iti.jets.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.InflaterInputStream;

@Getter
@Setter
@Entity
@Table(name = "employees", schema = "hr")
public class Employee extends BaseEntity {
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

    @Column(name = "vacation_balance")
    private Integer vacationBalance = 30;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_id", nullable = false)
    private Salary salary;

    @Column(name = "commission_pct", precision = 5, scale = 2)
    private BigDecimal commissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "manager")
    private Department managedDepartment;

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<JobHistory> jobhistories = new LinkedHashSet<>();



    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<VacationRequest> vacationRequests = new HashSet<>();



    @Transient
    private Integer managerId;

    @Transient
    private String departmentName;

    @Transient
    private String jobTitle;

    @Transient
    private Integer salaryId;

    @Transient
    private String managedDepartmentName;

    @Transient
    private BigDecimal salaryAmount;


    @PostLoad
    public void postLoad() {

        managerId = Optional.ofNullable(manager)
                .map(Employee::getId)
                .orElse(null);

        departmentName = Optional.ofNullable(department)
                .map(Department::getDepartmentName)
                .orElse("N/A");

        jobTitle = Optional.ofNullable(job)
                .map(Job::getJobTitle)
                .orElse("N/A");

        salaryId = Optional.ofNullable(salary)
                .map(Salary::getSalaryId)
                .orElse(null);

        salaryAmount = Optional.ofNullable(salary)
                .map(Salary::getPaymentAmount)
                .orElse(null);
        managedDepartmentName = Optional.ofNullable(managedDepartment)
                .map(Department::getDepartmentName)
                .orElse(null);
    }
    // to string
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", job=" + job +
                ", manager=" + manager +
                ", salary=" + salary +
                ", commissionPct=" + commissionPct +
                ", department=" + department +
                ", managedDepartment=" + managedDepartment +
                '}';
    }

}