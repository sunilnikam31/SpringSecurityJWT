package com.sunil.controller;

import com.sunil.model.Employee;
import com.sunil.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@PostMapping(value = "/post",consumes= { MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<?> getEmployee() {
		return ResponseEntity.ok(this.employeeService.getEmployee());
	}

	@GetMapping(value = "/edit/{id}",consumes= { MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity editEmployee(@PathVariable(name = "id") long id) {
		Employee employee = employeeService.editEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PutMapping(value = "/update",consumes= { MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity updateEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.updateEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}
	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}


}
