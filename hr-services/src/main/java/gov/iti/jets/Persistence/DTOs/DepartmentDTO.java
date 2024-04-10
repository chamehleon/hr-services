package gov.iti.jets.Persistence.DTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DepartmentDTO extends BaseDTO implements Serializable {
    private Integer id;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;

}
