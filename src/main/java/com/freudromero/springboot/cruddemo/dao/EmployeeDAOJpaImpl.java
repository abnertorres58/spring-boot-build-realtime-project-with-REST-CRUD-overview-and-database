package com.freudromero.springboot.cruddemo.dao;

import com.freudromero.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager;

    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Create a query
        Query theQuery = entityManager.createQuery("from Employee");

        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // Return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // Get employee and return it
        return entityManager.find(Employee.class, theId);
    }

    @Override
    public void save(Employee theEmployee) {

        // Save or update the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // Update with id from db ... so we can generate id for save/insert
        theEmployee.setId(dbEmployee.getId());

    }

    @Override
    public void deleteById(int theId) {

        // Delete the object with primary key
        Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();

    }
}
