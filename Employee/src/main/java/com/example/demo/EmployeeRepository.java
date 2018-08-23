package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	//This class acts as interface between Controller and employee table


}
