package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Entities.JobHistory;

public class JobHistoryDAO extends GenericDAO<JobHistory> {
    public JobHistoryDAO() {
        super(JobHistory.class);
    }


}
