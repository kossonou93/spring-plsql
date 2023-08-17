package com.oracle.plsql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.plsql.model.Employee;
import com.oracle.plsql.service.EmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value="/get/employee")
	@ResponseBody
	public List<Employee> getEmployeeList(@RequestBody Employee employee){
		List<Employee> employeeList = employeeService.getEmployeesList(employee);
		return employeeList;
	}
}
