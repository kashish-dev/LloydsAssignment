package com.sapient.lloydsAssignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sapient.lloydsAssignment.model.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Integer> {
	
	public List<Employee> findAllByPlace(String place);
	public List<Employee> findAllByTitle(String title);
	public List<Employee> findAllByBusinessUnit(String businessUnit);
	public List<Employee> findAllBySupervisorId(int supervisorId);
	public Employee findByEmployeeId(int employeeId);

}
