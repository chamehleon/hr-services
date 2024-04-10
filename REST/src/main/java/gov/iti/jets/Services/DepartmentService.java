package gov.iti.jets.Services;

import gov.iti.jets.Exceptions.ExceptionMessages.IllegalUpdateOperationException;
import gov.iti.jets.Persistence.DAOs.Implementations.DepartmentDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.EmployeeDAO;
import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Mappers.DepartmentMapper;
import gov.iti.jets.Persistence.Mappers.EmployeeMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DepartmentService implements GenericService<DepartmentDTO> {
    DepartmentDAO departmentDAO;

    public DepartmentService() {
         departmentDAO = new DepartmentDAO();
    }

    // delete department
    public boolean delete(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {

            departmentDAO.deleteById(id, entityManager);
            return departmentDAO.findById(id, entityManager) == null;
        });
    }

    // change department manager
    public DepartmentDTO changeDepartmentManager(Integer managerId, Integer departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            EmployeeService employeeService = new EmployeeService();
            Department department = departmentDAO.findById(departmentId, entityManager);
            Employee manager = employeeDAO.findById(managerId, entityManager);
            // TODO manager can have multiple departments?
            if (manager.getManagedDepartment() != null && !Objects.equals(manager.getManagedDepartment().getId(), departmentId)){
                throw new IllegalUpdateOperationException("Manager already manages a department");
            }
            manager.setManagedDepartment(department);
            department.setManager(manager);
            employeeService.update(EmployeeMapper.INSTANCE.toDto(manager));
            return DepartmentMapper.INSTANCE.toDto(departmentDAO.update(department, entityManager));
        });
    }
    // find department by name
    public DepartmentDTO findDepartmentByName(String name) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return DepartmentMapper.INSTANCE.toDto(departmentDAO.findByDepartmentName(name, entityManager));
        });
    }


    public List<DepartmentDTO> getAll(int page,int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return (List<DepartmentDTO>) DepartmentMapper.INSTANCE.collectionToDto(departmentDAO.findAll(entityManager,page,size));
        });
    }
    // get employees in department
    public Set<EmployeeDTO> getEmployeesInDepartment(int departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Department department = departmentDAO.findById(departmentId, entityManager);
            return new HashSet<>(EmployeeMapper.INSTANCE.collectionToDto(department.getEmployees()));
        });
    }
    // get department manager
    public EmployeeDTO getDepartmentManager(int departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Department department = departmentDAO.findById(departmentId, entityManager);
            return EmployeeMapper.INSTANCE.toDto(department.getManager());
        });
    }

    // add new department
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Department department = DepartmentMapper.INSTANCE.toEntity(departmentDTO);
            if (department.getManager() != null && department.getManager().getManagedDepartment() != null){
                throw new IllegalUpdateOperationException("Manager already manages a department");
            }
            return DepartmentMapper.INSTANCE.toDto(departmentDAO.create(department, entityManager));
        });
    }

    // update department
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Department department = DepartmentMapper.INSTANCE.toEntity(departmentDTO);
            return DepartmentMapper.INSTANCE.toDto(departmentDAO.update(department, entityManager));
        });
    }

    // partial update department
    public DepartmentDTO partialUpdateDepartment(DepartmentDTO departmentDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Department existing = departmentDAO.findById(departmentDTO.getId(), entityManager);
            Department newDepartment = (Department) DepartmentMapper.INSTANCE.partialUpdate(departmentDTO, existing);
            return DepartmentMapper.INSTANCE.toDto(newDepartment);
        });

    }
    // get department by id
    public DepartmentDTO getById(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return DepartmentMapper.INSTANCE.toDto(departmentDAO.findById(id, entityManager));
        });
    }
    // delete department
    public boolean deleteDepartment(Integer id) {
//        return JPATransactionManager.doInTransaction(entityManager -> {
//            Department department = departmentDAO.findById(id, entityManager);
//            department.setArchived(true);
//            return department.isArchived();
//        });
        return false;
    }


}
