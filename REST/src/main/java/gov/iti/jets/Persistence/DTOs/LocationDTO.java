package gov.iti.jets.Persistence.DTOs;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.io.Serializable;

@Data
@XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
public class LocationDTO extends BaseDTO implements Serializable {

    private Integer id;
    private String streetAddress;
    private String city;
    private String countryId;


}
