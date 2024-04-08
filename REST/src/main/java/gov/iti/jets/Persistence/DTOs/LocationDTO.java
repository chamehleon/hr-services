package gov.iti.jets.Persistence.DTOs;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDTO implements Serializable {

    private Integer id;
    private String streetAddress;
    private String city;
    private String countryId;


}
