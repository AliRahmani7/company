package com.beer;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.beer.model.Employee;
import com.beer.repository.EmployeeRepository;

@Component 
public class DataInitializer implements CommandLineRunner
{
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String...args) throws Exception
    {
        employeeRepository.save(new Employee("Jimmy", "Kartright", "jkartright@beer.com"));
        employeeRepository.save(new Employee("Tom", "Cruise", "tcruise@beer.com"));
        employeeRepository.save(new Employee("John", "Cena", "jcena@beer.com"));
        employeeRepository.save(new Employee("Tony", "stark", "tstark@beer.com"));

        Iterable < Employee > employees = employeeRepository.findAll();
        Iterator < Employee > iterator = employees.iterator();
        while (iterator.hasNext())
        {
            logger.info("{}", iterator.next().toString());
        }
    }
}
