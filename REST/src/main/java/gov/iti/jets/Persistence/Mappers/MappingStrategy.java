package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.BaseDTO;
import gov.iti.jets.Persistence.Entities.BaseEntity;
import org.mapstruct.MappingTarget;

public interface MappingStrategy<ENTITY extends BaseEntity, DTO extends BaseDTO> {
    void toEntityAfterMapping(@MappingTarget ENTITY entity, DTO dto);
}
