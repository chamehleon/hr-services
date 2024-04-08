package gov.iti.jets.Persistence.DTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDTO implements Serializable {
    private Integer id;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;

}
