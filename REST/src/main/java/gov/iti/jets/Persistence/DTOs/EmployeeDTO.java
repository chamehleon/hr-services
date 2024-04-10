package gov.iti.jets.Persistence.DTOs;


import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Data
public class EmployeeDTO extends BaseDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobTitle;
    private Integer managerId;


    private Integer salaryId;
    private BigDecimal salaryAmount;
    private BigDecimal commissionPct;

    private String departmentName;
    private String managedDepartmentName;
    private Integer vacationBalance;


}