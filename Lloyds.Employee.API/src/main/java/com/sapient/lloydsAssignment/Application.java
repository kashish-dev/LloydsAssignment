package com.sapient.lloydsAssignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sapient.lloydsAssignment.model.Employee;
import com.sapient.lloydsAssignment.repository.IEmployeeRepository;
import com.sapient.lloydsAssignment.service.EmployeeServiceImpl;


@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class Application {
	
	@Autowired
	public EmployeeServiceImpl service;
	
	@Autowired
	public IEmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		try {
			List<Employee> employeeList = service.getEmployees();
			repository.saveAll(employeeList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
