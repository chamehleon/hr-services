package gov.iti.jets.Persistence.DTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.io.Serializable;

@Data
public class JobDTO implements Serializable {
    private Integer id;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;


}
