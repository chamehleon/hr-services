package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Entities.JobHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper extends GenericMapper<Job, JobDTO>{
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Override
    default void toEntityAfterMapping(@MappingTarget Job job, JobDTO jobDTO) {

    }




}
