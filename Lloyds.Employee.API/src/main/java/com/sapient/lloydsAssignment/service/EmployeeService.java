package com.sapient.lloydsAssignment.service;

import java.util.List;

import com.sapient.lloydsAssignment.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	public Double findTotalSalary(List<Employee> employees);
	public Double findRangeOfSalaries(List<Employee> employees);
	public Double changeSalaryByPercentage(Double percentage);
	public String getHierarchy(Employee employee);

}
