package gov.iti.jets.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "locations", schema = "hr")
public class Location extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Size(max = 255)
    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @Size(max = 255)
    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "location")
    private Set<Department> departments = new LinkedHashSet<>();

}