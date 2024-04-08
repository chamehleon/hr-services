package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.LocationDAO;
import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.Mappers.LocationMapper;
import gov.iti.jets.Utils.JPATransactionManager;

public class LocationService {

    // find location by id
    public LocationDTO findLocationById(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            LocationDAO locationDAO = new LocationDAO();
            return LocationMapper.INSTANCE.toDto(locationDAO.findById(id, entityManager));
        });
    }



}
