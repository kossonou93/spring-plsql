package com.oracle.plsql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.plsql.model.Employee;
import com.oracle.plsql.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployeesList(Employee employee){
		List<Employee> employeesList = employeeRepository.getEmployeesList(employee);
		return employeesList;
	}

}
