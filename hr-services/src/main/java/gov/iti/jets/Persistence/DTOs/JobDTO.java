package gov.iti.jets.Persistence.DTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class JobDTO extends BaseDTO implements Serializable {
    private Integer jobId;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private boolean deleted;


}
