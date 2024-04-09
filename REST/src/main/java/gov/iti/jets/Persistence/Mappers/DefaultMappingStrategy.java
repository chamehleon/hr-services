package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.BaseDTO;
import gov.iti.jets.Persistence.Entities.BaseEntity;
import org.mapstruct.MappingTarget;

public class DefaultMappingStrategy<ENTITY extends BaseEntity, DTO extends BaseDTO> implements MappingStrategy<ENTITY, DTO> {
    @Override
    public void toEntityAfterMapping(@MappingTarget ENTITY entity, DTO dto) {
        // do nothing
    }
}
