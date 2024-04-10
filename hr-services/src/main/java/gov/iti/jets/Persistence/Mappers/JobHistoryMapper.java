package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Persistence.Entities.JobHistory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobHistoryMapper extends GenericMapper<JobHistory, JobHistoryDTO>{
    JobHistoryMapper INSTANCE = Mappers.getMapper(JobHistoryMapper.class);
    @Override
    default void toEntityAfterMapping(@MappingTarget JobHistory jobHistory, JobHistoryDTO jobHistoryDTO) {

    }



}
