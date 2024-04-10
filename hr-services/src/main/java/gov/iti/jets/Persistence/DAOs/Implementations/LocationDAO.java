package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Location;

public class LocationDAO extends GenericDAO<Location> {
    public LocationDAO() {
        super(Location.class);
    }

}
