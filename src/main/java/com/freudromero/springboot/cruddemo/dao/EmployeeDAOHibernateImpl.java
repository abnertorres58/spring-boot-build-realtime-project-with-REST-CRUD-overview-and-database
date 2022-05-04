package com.freudromero.springboot.cruddemo.dao;

import com.freudromero.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    // Define field for entitymanager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // Create a query
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // Return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // Get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // Get the employee
        Employee theEmployee = currentSession.get(Employee.class, theId);

        // Return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {

        // Get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // Save employee
        // Remember if id=0 then save/insert else update
        currentSession.saveOrUpdate(theEmployee);

    }

    @Override
    public void deleteById(int theId) {

        // Get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // Delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();
    }
}
