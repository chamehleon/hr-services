package gov.iti.jets.Persistence.DTOs;

import gov.iti.jets.SoapServices.Adapters.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
public class VacationRequestDTO  extends BaseDTO implements Serializable{
    private Integer id;
    private EmployeeDTO employee;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate endDate;
    private String status;


}
