package com.sunil.controller;

import com.sunil.model.Employee;
import com.sunil.model.SequenceGeneratorService;
import com.sunil.repository.EmployeeRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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


	@PostMapping(value = "/post",consumes= { MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity saveEmployee(@RequestBody Employee employee) {
		employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		Employee emp = repository.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<?> getEmployee() {
		return ResponseEntity.ok(this.repository.findAll());
	}

	@GetMapping(value = "/edit/{id}",consumes= { MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity editEmployee(@PathVariable(name = "id") long id) {
		Employee employee = repository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PutMapping(value = "/update",consumes= { MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity updateEmployee(@RequestBody Employee employee) {
		Employee emp = repository.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}



}
