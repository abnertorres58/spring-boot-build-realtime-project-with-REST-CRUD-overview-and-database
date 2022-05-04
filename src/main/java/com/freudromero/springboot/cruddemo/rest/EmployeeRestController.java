package com.freudromero.springboot.cruddemo.rest;

import com.freudromero.springboot.cruddemo.dao.EmployeeDAO;
import com.freudromero.springboot.cruddemo.entity.Employee;
import com.freudromero.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

   private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }


    // Expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        if(employeeService.findById(employeeId) == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employeeService.findById(employeeId);
    }


    // Add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // Also just in case they pass an id in JSON ... set id to 0
        // This is  to force a save of new item ... instead of update
        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    }



}
