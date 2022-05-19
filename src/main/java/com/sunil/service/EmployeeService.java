package com.sunil.service;

import com.sunil.model.Employee;
import com.sunil.model.SequenceGeneratorService;
import com.sunil.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    EmployeeRepository repository;

    public Employee saveEmployee(Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        Employee emp = repository.save(employee);
        return emp;
    }
    public List<Employee> getEmployee() {
        return repository.findAll();
    }

    public Employee editEmployee(long id) {
        Employee employee = repository.findById(id);
        return employee;
    }
    public Employee updateEmployee(Employee employee) {
        Employee emp = repository.save(employee);
        return emp;
    }

}

