package gov.iti.jets.Persistence.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SalaryDTO implements Serializable {
    private Integer salaryId;
    private BigDecimal paymentAmount;
    private BigDecimal deductions;
    private BigDecimal bonuses;

}
