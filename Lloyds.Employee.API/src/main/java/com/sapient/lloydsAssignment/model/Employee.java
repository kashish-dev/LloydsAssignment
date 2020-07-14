package com.sapient.lloydsAssignment.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Employee {
	
	public Employee() {
		
	}
	
	
	public Employee(int employeeId, String employeeName, String title, String businessUnit, String place,
			int supervisorId, List<String> competencies, double salary) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.title = title;
		this.businessUnit = businessUnit;
		this.place = place;
		this.supervisorId = supervisorId;
		this.competencies = competencies;
		this.salary = salary;
	}

	@Id
	private Integer employeeId;
	private String employeeName;
	private String title;
	private String businessUnit;
	private String place;
	private Integer supervisorId;
	@Column
    @ElementCollection(targetClass=String.class)
	private List<String> competencies;
	private Double salary;
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}

	public List<String> getCompetencies() {
		return competencies;
	}

	public void setCompetencies(List<String> competencies) {
		this.competencies = competencies;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
}


