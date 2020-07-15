package com.sapient.lloydsAssignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.lloydsAssignment.model.Employee;
import com.sapient.lloydsAssignment.repository.IEmployeeRepository;
import com.sapient.lloydsAssignment.service.EmployeeServiceImpl;

/**
 * @author kasjain
 * This is the controller class which controls the flow of the data.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	public EmployeeServiceImpl service;
	
	@Autowired
	public IEmployeeRepository repository;
		
	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@CachePut(value="employeeByPlace",key="#place")
	@PutMapping("/place/{place}/salary/{percentage}")
	public Iterable<Employee> updateEmployee(@RequestBody @PathVariable String place, @PathVariable Double percentage){
		Iterable<Employee> employees = repository.findAllByPlace(place);
		double factor = service.changeSalaryByPercentage(percentage);
		employees.forEach(employee -> employee.setSalary(employee.getSalary()*factor));
		if(employees.iterator().hasNext()) {
			repository.saveAll(employees);
		}
		return employees;
	}
	
	@GetMapping("/place/{place}")
	@Cacheable(value="employeeByPlace",key="#place")
	public List<Employee> getEmployeesByPlace(@PathVariable String place){
		String msg = "Fetching employees by place from DB";
		logger.info(msg);
		return repository.findAllByPlace(place);
	}
	
	@GetMapping("/totalSalary/BU/{businessUnit}")
	public double totalSalaryByBusinessUnit(@PathVariable String businessUnit){
		List<Employee> employees = repository.findAllByBusinessUnit(businessUnit);
		double totalSalary = service.findTotalSalary(employees);
		return totalSalary;
	}
	
	@GetMapping("/totalSalary/supervisor/{supervisorId}")
	public double totalSalaryBySupervisor(@PathVariable int supervisorId){
		List<Employee> employees = repository.findAllBySupervisorId(supervisorId);
		double totalSalary = service.findTotalSalary(employees);
		return totalSalary;
	}
	
	@GetMapping("/totalSalary/place/{place}")
	public double totalSalaryBySupervisor(@PathVariable String place){
		List<Employee> employees = repository.findAllByPlace(place);
		double totalSalary = service.findTotalSalary(employees);
		return totalSalary;
	}
	
	@GetMapping("range/salary/{title}")
	public Double rangeOfSalaries(@PathVariable String title) {
		List<Employee> employees = repository.findAllByTitle(title);
		if(employees.size() == 0) {
			return 0.0;
		}
		return service.findRangeOfSalaries(employees);
	}
	
	@GetMapping("/nestedlist/id/{employeeId}")
	public String getNestedListOfSupervisees(@PathVariable Integer employeeId) {
		Employee employee =repository.findByEmployeeId(employeeId);
		return service.getHierarchy(employee);
	}
	
	
}
