package com.sunil.controller;

import com.sunil.model.Employee;
import com.sunil.model.SequenceGeneratorService;
import com.sunil.repository.EmployeeRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class EmployeeController {
	@Autowired
	EmployeeRepository repository;
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;


	@RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Employee saveEmployee(@RequestBody Employee employee) {
		employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		Employee emp = repository.save(employee);
		return emp;
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<?> getEmployee() {
		return ResponseEntity.ok(this.repository.findAll());
	}

	@RequestMapping(value = "/edit/{id}", produces = "application/json", method = RequestMethod.GET)
	public Employee editEmployee(@PathVariable(name = "id") long id) {
		Employee employee = repository.findById(id);
		System.out.println(employee);
		return employee;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee emp = repository.save(employee);
		return emp;
	}

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}



}
