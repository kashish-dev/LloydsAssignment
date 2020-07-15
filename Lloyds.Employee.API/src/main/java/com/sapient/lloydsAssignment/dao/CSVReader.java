package com.sapient.lloydsAssignment.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sapient.lloydsAssignment.model.Employee;

/**
 * @author kasjain
 * This class is responsible for reading csv file and returning the List of all employees.
 */
@Service
public class CSVReader implements Reader {
	
	private  static Logger logger = LoggerFactory.getLogger(CSVReader.class);

	//This method provides list of employees read from CSV file.
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		String fileName = "employee.csv";
		MappingIterator<Employee> employeeData;
		try {
			employeeData = readCsv(fileName);
			while(employeeData.hasNext()) {
				Employee e = employeeData.next();
				employees.add(e);
			}
			return employees;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//This method reads data from CSV file.
	private static MappingIterator<Employee> readCsv(String fileName) throws IOException {
        CsvSchema bootstrap = CsvSchema.builder()
                .addColumn("employeeId", CsvSchema.ColumnType.NUMBER)
                .addColumn("employeeName", CsvSchema.ColumnType.STRING)
                .addColumn("title", CsvSchema.ColumnType.STRING)
                .addColumn("businessUnit", CsvSchema.ColumnType.STRING)
                .addColumn("place", CsvSchema.ColumnType.STRING)
                .addColumn("supervisorId", CsvSchema.ColumnType.NUMBER)
                .addArrayColumn("competencies", ' ')
                .addColumn("salary", CsvSchema.ColumnType.NUMBER)
                .build().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        File file = new ClassPathResource(fileName).getFile();
        return csvMapper.readerFor(Employee.class).with(bootstrap).readValues(file);
    }

}
