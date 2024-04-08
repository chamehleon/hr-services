package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.Entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDTO toDto(Location entity);

    Location toEntity(LocationDTO dto);
}
