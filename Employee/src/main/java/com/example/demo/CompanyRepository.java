package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	//This class acts as interface between Controller and companyTable
}
