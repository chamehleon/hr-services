package gov.iti.jets.Persistence.DTOs;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobTitle;  // Assuming you want to include jobId instead of Job object
    private String managerName;  // Assuming you want to include managerId instead of Employee object
    private BigDecimal salaryAmount;  // Assuming you want to include salaryId instead of Salary object
    private BigDecimal commissionPct;
    private String departmentName;  // Assuming you want to include departmentId instead of Department object
   // private Set<Integer> attendanceIds;  // Assuming you want to include attendanceIds instead of Attendance objects
   // private Integer departmentManagerId;  // Assuming you want to include departmentManagerId instead of Employee object






}