package gov.iti.jets.Persistence.DTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
public class VacationRequestDTO  extends BaseDTO implements Serializable{
    private Integer id;
    private EmployeeDTO employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;


}
