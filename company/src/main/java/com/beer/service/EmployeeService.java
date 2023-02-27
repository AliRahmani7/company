package com.beer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer.exception.RecordNotFoundException;
import com.beer.model.Employee;
import com.beer.repository.EmployeeRepository;
 
@Service
public class EmployeeService
{  
    @Autowired
    EmployeeRepository repository;
     
    public List<Employee> getAllEmployees()
    {
        List<Employee> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) 
            return employeeList;
        else 
            return new ArrayList<Employee>();
    }
     
    public Employee getEmployee(Long id) throws RecordNotFoundException
    {
        Optional<Employee> employee = repository.findById(id);
         
        if(employee.isPresent()) 
            return employee.get();
        else 
            throw new RecordNotFoundException("No employee found for " +id);    
    }
     
    public Employee createUpdateEmployee(Employee emp) throws RecordNotFoundException
    {
        Optional<Employee> employee = repository.findById(emp.getId());
         
        if(employee.isPresent())
        {
            Employee newEmployee = employee.get();
            newEmployee.setFirstName(emp.getFirstName());
            newEmployee.setLastName(emp.getLastName());
            newEmployee.setEmail(emp.getEmail());

            return repository.save(newEmployee);
        } 
        else 
            return  repository.save(emp);
    }
     
    public void deleteEmployee(Long id) throws RecordNotFoundException
    {
        Optional<Employee> employee = repository.findById(id);
         
        if(employee.isPresent())
            repository.deleteById(id);
        else 
            throw new RecordNotFoundException("No employee found for " + id);    
    }
}