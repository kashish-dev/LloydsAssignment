package com.sapient.lloydsAssignment.service;

import java.util.List;

import com.sapient.lloydsAssignment.model.Employee;

/**
 * @author kasjain
 * This is an interface used to specify a behaviour that EmployeeServiceImpl class must implement.
 */
public interface EmployeeService {
	
	public List<Employee> getEmployees();
	public Double findTotalSalary(List<Employee> employees);
	public Double findRangeOfSalaries(List<Employee> employees);
	public Double changeSalaryByPercentage(Double percentage);
	public String getHierarchy(Employee employee);

}
