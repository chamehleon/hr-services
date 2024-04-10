package gov.iti.jets.Persistence.DTOs;

import gov.iti.jets.SoapServices.Adapters.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
public class JobHistoryDTO extends BaseDTO implements Serializable{
    private Integer jobHistoryId;
    private EmployeeDTO employee;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate endDate;
    private JobDTO job;
    private DepartmentDTO department;

}
