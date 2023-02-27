package com.beer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beer.exception.RecordNotFoundException;
import com.beer.model.Employee;
import com.beer.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController
{	
	@Autowired      
	private EmployeeService employeeService;

	 // @formatter:off
    @Operation(summary = "Employees who drink Beer")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Found the employee", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content), 
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) }) // @formatter:on
    
    @GetMapping("/")										
	String testServer()
    {
	    return "Server Running !";
	}
    
	@PostMapping("/addEmployee")
	public Employee create(@RequestBody Employee employee) throws RecordNotFoundException 
	{
		return employeeService.createUpdateEmployee(employee);
	}
	
	@GetMapping("/allEmployees")
	public List<Employee> getAll() 
	{
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getById(@PathVariable Long id) throws RecordNotFoundException 
	{
		return employeeService.getEmployee(id);
	}
	
	@PutMapping("/employee/{id}") 
	public Employee update(@PathVariable Long id, @RequestBody Employee employee) throws RecordNotFoundException
	{     
        if(employeeService.getEmployee(id) !=null)
        	employee = employeeService.createUpdateEmployee(employee);
             
        return employee;
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws RecordNotFoundException
	{   
		 if(employeeService.getEmployee(id) !=null)
			 employeeService.deleteEmployee(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
