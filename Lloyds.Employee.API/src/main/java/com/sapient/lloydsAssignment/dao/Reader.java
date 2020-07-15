package com.sapient.lloydsAssignment.dao;

import java.util.List;

import com.sapient.lloydsAssignment.model.Employee;

/**
 * @author kasjain
 * This is an interface used to specify a behaviour that CSVReader class must implement.
 */
public interface Reader {

	public List<Employee> getEmployees();
}
