package gov.iti.jets.Persistence.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class JobHistoryDTO extends BaseDTO implements Serializable{
    private Integer jobHistoryId;
    private EmployeeDTO employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private JobDTO job;
    private DepartmentDTO department;

}
