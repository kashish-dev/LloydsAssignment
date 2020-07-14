package com.sapient.lloydsAssignment.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sapient.lloydsAssignment.model.Employee;
import com.sapient.lloydsAssignment.service.EmployeeService;
import com.sapient.lloydsAssignment.service.EmployeeServiceImpl;

class EmployeeServiceTest {
	
	private static EmployeeService service;
	private static List<Employee> employeeList;
	private static List<String> competencies;
	
	@BeforeAll
	public static void initialize() {
		service = new EmployeeServiceImpl();
		employeeList = new ArrayList<Employee>();
		competencies = new ArrayList<String>();
	}

	@Test
	public void testFindTotalSalaryOne() {
		employeeList.add(new Employee(
				101,"A","B","C","D",1001,competencies,400000
		));
		employeeList.add(new Employee(
				102,"E","F","G","H",1001,competencies,250000
		));
		Double expected = (double) 650000;
		double actual = service.findTotalSalary(employeeList);
		assertEquals(expected, actual);
		employeeList.clear();
	}
	
	@Test
	public void testFindTotalSalaryTwo() {
		employeeList.add(new Employee(
				101,"A","B","C","D",1001,competencies,400000.23
		));
		employeeList.add(new Employee(
				102,"E","F","G","H",1001,competencies,250000.5
		));
		Double expected = (double) 650000.73;
		double actual = service.findTotalSalary(employeeList);
		assertEquals(expected, actual);
		employeeList.clear();
	}
	
	@Test
	public void testFindTotalSalaryThree() {
		employeeList.add(new Employee(
				101,"A","B","C","D",1001,competencies,400000.9643
		));
		employeeList.add(new Employee(
				102,"E","F","G","H",1001,competencies,250000
		));
		Double expected = (double) 650000.9643;
		double actual = service.findTotalSalary(employeeList);
		assertEquals(expected, actual);
		employeeList.clear();
	}
	
	@Test
	public void testFindRangeOfSalariesOne() {
		employeeList.add(new Employee(
				101,"A","B","C","D",1001,competencies,400000
		));
		employeeList.add(new Employee(
				102,"E","F","G","H",1001,competencies,250000
		));
		employeeList.add(new Employee(
				103,"P","Q","R","S",1001,competencies,950000
		));
		Double expected = (double) 700000;
		double actual = service.findRangeOfSalaries(employeeList);
		assertEquals(expected, actual);
		employeeList.clear();
	}
	
	@Test
	public void testFindRangeOfSalariesTwo() {
		employeeList.add(new Employee(
				101,"A","B","C","D",1001,competencies,400000.67
		));
		employeeList.add(new Employee(
				102,"E","F","G","H",1001,competencies,250000.8904
		));
		employeeList.add(new Employee(
				103,"P","Q","R","S",1001,competencies,950000.3
		));
		Double expected = (double) 699999.4096;
		double actual = service.findRangeOfSalaries(employeeList);
		assertEquals(expected, actual);
		employeeList.clear();
	}
	
	@Test
	public void testChangeSalaryByPercentageOne() {
		double actual = service.changeSalaryByPercentage(0.0);
		Double expected = (double) 1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testChangeSalaryByPercentageTwo() {
		double actual = service.changeSalaryByPercentage(2.0);
		Double expected = (double) 1.02;
		assertEquals(expected, actual);
	}
	@Test
	public void testChangeSalaryByPercentageThree() {
		double actual = service.changeSalaryByPercentage(-2.0);
		Double expected = (double) 1;
		assertEquals(expected, actual);
	}
	
	

}
