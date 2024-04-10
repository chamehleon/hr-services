package gov.iti.jets.Persistence.DTOs;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.io.Serializable;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IslamDTO extends BaseDTO implements Serializable {
    private Integer id;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
