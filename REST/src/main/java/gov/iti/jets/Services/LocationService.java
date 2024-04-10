package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.LocationDAO;
import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.Entities.Location;
import gov.iti.jets.Persistence.Mappers.LocationMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.List;
import java.util.stream.Collectors;

public class LocationService implements GenericService<LocationDTO>{

    // find location by id
    public LocationDTO getById(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            LocationDAO locationDAO = new LocationDAO();
            return LocationMapper.INSTANCE.toDto(locationDAO.findById(id, entityManager));
        });
    }

    // get all locations
    public List<LocationDTO> getAll(int page, int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            LocationDAO locationDAO = new LocationDAO();
            List<Location> locations = locationDAO.findAll(entityManager, page, size);
            return locations.stream().map(LocationMapper.INSTANCE::toDto).collect(Collectors.toList());
        });
    }

    // add location
    public LocationDTO create(LocationDTO locationDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            LocationDAO locationDAO = new LocationDAO();
            Location location = LocationMapper.INSTANCE.toEntity(locationDTO);
            return LocationMapper.INSTANCE.toDto(locationDAO.create(location, entityManager));
        });
    }

    // update location
    public LocationDTO update(LocationDTO locationDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            LocationDAO locationDAO = new LocationDAO();
            Location existing = locationDAO.findById(locationDTO.getId(), entityManager);
            Location newLocation = (Location) LocationMapper.INSTANCE.toEntity(locationDTO);
            locationDAO.update(newLocation, entityManager);
            return LocationMapper.INSTANCE.toDto(newLocation);
        });
    }

    // delete location
    public boolean delete(Integer locationId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            LocationDAO locationDAO = new LocationDAO();
            Location location = locationDAO.findById(locationId, entityManager);
            return locationDAO.delete(location, entityManager);
        });
    }



}
