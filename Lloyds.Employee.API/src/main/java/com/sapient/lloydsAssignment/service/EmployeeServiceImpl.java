package com.sapient.lloydsAssignment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.lloydsAssignment.dao.Reader;
import com.sapient.lloydsAssignment.model.Employee;
import com.sapient.lloydsAssignment.repository.IEmployeeRepository;

/**
 * @author kasjain
 * This class is responsible for handling all business logic needed for the project.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private Reader reader;
	
	@Autowired
	public EmployeeServiceImpl(Reader r) {
		this.reader = r;
	}
	
	@Autowired
	public IEmployeeRepository repository;
	
	/**
	 * Constructor of the class.
	 */
	public EmployeeServiceImpl() {
		
	}
	
	//This method provides list of employees read from CSV file.
	public List<Employee> getEmployees(){
		return reader.getEmployees();
	}
	
	//This method finds total salary of given employees.
	public Double findTotalSalary(List<Employee> employees) {
		Employee[] e = new Employee[employees.size()];
		double totalSalary = Arrays.stream(employees.toArray(e))
                .filter(Objects::nonNull)
                .mapToDouble(Employee::getSalary)
                .sum();
		return totalSalary;
	}
	
	//This method finds range of salary of given employees.
	public Double findRangeOfSalaries(List<Employee> employees) {
		List<Double> salaries = new ArrayList<Double>();
		employees.forEach(employee -> salaries.add(employee.getSalary()));
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < salaries.size(); i++){
	        double elementValue = salaries.get(i);
	        max = Math.max(max, elementValue);
	        min = Math.min(min, elementValue);
	    }
		return (max-min);
	}
	
	//This method provides percentage change.
	public Double changeSalaryByPercentage(Double percentage) {
		percentage = Math.max(0.0, percentage);
		Double factor = 1+percentage/100.0;
		return factor;
	}

	//This method prints hierarchy of a given employee.
	public String getHierarchy(Employee employee) {
		String ret = "";
		Queue<Employee> q = new LinkedList<Employee>();
		q.add(employee);
		
		//this variable holds the number of child we have in current level
		int currChildSize = 1;
		int nextChildSize = 0;	//this variable holds the number of child in the next level
		int processedOnThisLevel = 0; //this variable holds the number of child we have processed in the current level
		while (q.size() > 0) {
			Employee e = q.peek();
			q.remove();
			
			//print the current node
			ret += e.getEmployeeName() + " -> ";
			List<Employee> children = getSupervisees(e.getEmployeeId());
			//print its children
			for (Employee c : children) {
				q.add(c);
				ret += c.getEmployeeName() + ",";
			}

			nextChildSize += children.size();
			processedOnThisLevel += 1;

			//we have completed this level, proceed the variables for the next level processing
			if (processedOnThisLevel == currChildSize) {
				currChildSize = nextChildSize;
				nextChildSize = 0;
				processedOnThisLevel = 0;
			}
			
			//print a new line
			ret += "<br/>";

		}
		return ret;
	}
	
	//This method returns a list of employees who are supervised by a particular employee.
	public List<Employee> getSupervisees(Integer employeeId){
		return repository.findAllBySupervisorId(employeeId);
	}

}


 
