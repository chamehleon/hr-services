package gov.iti.jets;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.Entities.*;
import gov.iti.jets.Persistence.Mappers.EmployeeMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("PU");
        EntityManager em  = emf.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();
        Job job = new Job();
        job.setJobTitle("Software Engineer");
        job.setMinSalary(new BigDecimal("1000"));
        job.setMaxSalary(new BigDecimal("10000"));
        em.persist(job);


        Salary salary = new Salary();
        salary.setPaymentAmount(new BigDecimal("10000"));
        em.persist(salary);


        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@example.com");
        employee1.setPhoneNumber("1234567890");
        employee1.setHireDate(LocalDate.now());
        employee1.setCommissionPct(new BigDecimal("0.10"));
        employee1.setJob(job);
        employee1.setSalary(salary);
        em.persist(employee1);


        Department department = new Department();
        department.setDepartmentName("IT");
        department.setManager(employee1);

        Location location = new Location();
        location.setCity("Cairo");
        location.setStreetAddress("Cairo");
        location.setCountry("Egypt");
        em.persist(location);

        department.setLocation(location);

        em.persist(department);
        employee1.setManagedDepartment(department);
        employee1.setDepartment(department);





















        // add to the database employee while alseo creating salary and job and manager and department and then commit the transaction
//        Employee employee2 = new Employee();
//        employee2.setFirstName("Jane");
//        employee2.setLastName("Doe");
//        employee2.setEmail("jandoe.coom");
//        employee2.setPhoneNumber("1234567890");
//        employee2.setHireDate(LocalDate.now());
//        employee2.setCommissionPct(new BigDecimal("0.10"));
//
//        employee2.setSalary(salary);
//        employee2.setJob(job);
//        employee2.setManager(employee1);
//
//
//
//        employee2.setDepartment(department);
//        em.persist(employee1);
//        em.persist(employee2);
        transaction.commit();





    }
}
