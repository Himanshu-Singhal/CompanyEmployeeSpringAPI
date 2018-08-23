package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {

	@Autowired
	private EmployeeRepository employeeDao;
	@Autowired
	private CompanyRepository companyDao;



	
	// ------------------------------------------------
	
	
	// SQL Query Running Behind :
	// DELETE FROM employee
	// WHERE id = ________;

	// API End point: http://localhost:8080/deleteemployee/id=_____
	@RequestMapping("/deleteemployee")
	@ResponseBody
	public String deleteEmployee(Long id) {

		try {
			// delete by id . id comes from parameter needs to be long
			employeeDao.deleteById(id);

			return "Deleted Employee" + id.toString();

		}

		catch (Exception ex) {
			return "Error creating new employee:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// DELETE * FROM employee;

	// API End point: http://localhost:8080/deleteallemployee/
	@RequestMapping("/deleteallemployee")
	@ResponseBody
	public String DeleteAllEmployee() {

		try {
			employeeDao.deleteAll();
			return "Deleted Employee";

		}

		catch (Exception ex) {
			return "Error Deleting all Employee:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// UPDATE employee
	// SET NAME = ______
	// WHERE id = _______ ;

	// API End point: http://localhost:8080/updateemployee/id=___&name=______
	@RequestMapping("/updateemployee")
	@ResponseBody
	public String UpdateEmployee(Long id, String name) {

		try {

			// get artist object by id if null return
			Employee x = employeeDao.findById(id).orElse(null);

			if (x == null) {
				return "cannot find an comapany with id " + id.toString();
			}

			// update the field use setter function
			x.setName(name);

			// save the updated object
			employeeDao.save(x);

			return "updated Employee" + x.toString();

		} catch (Exception ex) {
			return "Error Updating the Employee:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// SELECT * from employee

	// API End point: http://localhost:8080/selectallemployees/
	@RequestMapping("/selectallemployees")
	@ResponseBody
	public Iterable<Employee> getAllEmployees() {
		return employeeDao.findAll();

	}

	
	// SQL Query Running Behind :
	// UPDATE Employee SET NAME = ______
	// WHERE ID = _______;

	// API End point: http://localhost:8080/createemployee/employeeName=______&companyId=______
	@RequestMapping("/createemployee")
	@ResponseBody
	public String CreateEmployee(String employeeName, Long companyId) {

		try {

			// 1. Ask database to get the Company with id=companyId
			// If db can find it, then good -> store it in the "c" variable
			// Otherwise, store "null" in the "c" variable
			Company c = companyDao.findById(companyId).orElse(null);

			if (c == null) {
				// 1b. if no company is found, then exit
				return "No company with id: " + companyId + ". Exiting!";
			}
			
			
			// 2. Create a new Employee object using the company & employeeName
			Employee e = new Employee(employeeName, c);


			// 3. Save employee to database
			this.employeeDao.save(e);

			return "New Employee created :" + e.toString();
			
		} catch (Exception ex) {

			ex.printStackTrace();

			return null;

		}

	}


	
	// SQL Query Running Behind :
	// SELECT employee.name, company.name
	// FROM employee 
	// INNER JOIN employee.company_id = company.id 
	// 
	
	// API End point: http://localhost:8080/selectemployeebycompany
	@RequestMapping("/selectemployeebycompany")
	@ResponseBody
	public String selectAllEmployees(Long id) {

		return "todo!";

	}


	
	
	// ------------------------------------------------
	
	
	// SQL Query Running Behind :
	// INSERT INTO company (name)
	// VALUES ('_________');

	// API End point: http://localhost:8080/createcompany/name=___
	@RequestMapping("/createcompany")
	@ResponseBody
	public String CreateCompany(String name) {

		try {
			Company x = new Company(name);
			companyDao.save(x);
			return "Created New Company" + x.toString();
		}

		catch (Exception ex) {
			return "Error Creating New Company:" + ex.toString();
		}

	}

	// SQL Query Running Behind :
	// DELETE FROM company
	// WHERE id = ________

	// API End point: http://localhost:8080/deletecompany/id=______
	@RequestMapping("/deletecompany")
	@ResponseBody
	public String DeleteCompany(Long id) {

		try {
			companyDao.deleteById(id);
			return "Deleted company" + id.toString();

		}

		catch (Exception ex) {
			return "Error creating new company:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// DELETE * FROM company;

	// API End point: http://localhost:8080/deleteallcompany/
	@RequestMapping("/deleteallcompany")
	@ResponseBody
	public String DeleteAllCompany() {
		try {
			companyDao.deleteAll();
			return "Deleted company";

		}

		catch (Exception ex) {
			return "Error Deleting all company:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// UPDATE Company SET NAME = ______
	// WHERE id = _____;

	// API End point: http://localhost:8080/updatecompany?id=_______&name=_______
	@RequestMapping("/updatecompany")
	@ResponseBody
	public String UpdateCompany(Long id, String name) {

		try {

			Company x = companyDao.findById(id).orElse(null);

			if (x == null) {
				return "cannot find an comapany with id " + id.toString();
			}

			// update the field use setter function
			x.setName(name);

			// save the updated object
			companyDao.save(x);

			return "updated company" + x.toString();

		} catch (Exception ex) {
			return "Error Updating the company:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// SELECT * FROM company;

	// API End point: http://localhost:8080/selectallcompany/Id=______
	@RequestMapping("/selectallcompany")
	@ResponseBody
	public Iterable<Company> getAllCompanies() {
		return companyDao.findAll();

	}


}
