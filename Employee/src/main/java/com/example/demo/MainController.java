package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	// API End point: http://localhost:8080/employee/25
	@DeleteMapping("/employee/{id}")
	@ResponseBody
	public String deleteEmployee(@PathVariable("id") Long id) {

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

	// API End point: http://localhost:8080/employee/
	@DeleteMapping("/employee")
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

	// API End point: http://localhost:8080/employee/id=25
	@PutMapping("/employee/{id}")
	@ResponseBody
	public String UpdateEmployee(@PathVariable("id") Long id, String name) {

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

	// API End point: http://localhost:8080/employees/
	@RequestMapping("/employees")
	@ResponseBody
	public Iterable<Employee> getAllEmployees() {
		return employeeDao.findAll();

	}

	// SQL Query Running Behind :
	// UPDATE Employee SET NAME = ______
	// WHERE ID = _______;

	// API End point:
	// http://localhost:8080/createemployee/employee
	@PutMapping("/employee")
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
	@RequestMapping("employee/{id")
	@ResponseBody
	public String selectAllEmployees(@PathVariable("id") Long id) {

		return "todo!";

	}

	// ------------------------------------------------

	// SQL Query Running Behind :
	// INSERT INTO company (name)
	// VALUES ('_________');

	// API End point: http://localhost:8080/createcompany/name=___
	@PostMapping("/company")
	@ResponseBody
	public Company CreateCompany(String name) {

		try {
			Company x = new Company(name);
			companyDao.save(x);
			//return "Created New Company" + x.toString();
			return x;
		}

		catch (Exception ex) {
			//return "Error Creating New Company:" + ex.toString();
			return null;
		}

	}

	// SQL Query Running Behind :
	// DELETE FROM company
	// WHERE id = ________

	// API End point: http://localhost:8080/deletecompany/id=______
	@DeleteMapping("/company/{id}")
	@ResponseBody
	public String DeleteCompany(@PathVariable("id") Long id) {

		try {
			companyDao.deleteById(id);
			return "Deleted company" + id.toString();

		}

		catch (Exception ex) {
			return "Error Deleting company:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// DELETE * FROM company;

	// API End point: http://localhost:8080/deleteallcompany/
	@DeleteMapping("/company")
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
	@PutMapping("/company/{id}")
	@ResponseBody
	public Company UpdateCompany(@PathVariable("id") Long id, String name) {

		try {

			Company x = companyDao.findById(id).orElse(null);

			if (x == null) {
				System.out.println("cannot find an comapany with id " + id.toString());
				return x;
			}

			// update the field use setter function
			x.setName(name);

			// save the updated object
			companyDao.save(x);

			System.out.println("updated company" + x.toString());
			return x;
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			//return "Error Updating the company:" + ex.toString();
			return null;
		}
	}

	// SQL Query Running Behind :
	// SELECT * FROM company;

	// API End point: http://localhost:8080/selectallcompany/Id=______
	//@RequestMapping("/select/company/all")
	@RequestMapping("/company")
	@ResponseBody
	public Iterable<Company> getAllCompanies() {
		return companyDao.findAll();

	}
	
	
	

	// SQL Query Running Behind :
	// SELECT name FROM company
	// where id = ________;
	
	@RequestMapping("/company/{id}")		// DYNAMIC URL
	@ResponseBody
	public Company selectCompany(@PathVariable("id") Long id) {
		try {
			Company x = companyDao.findById(id).orElse(null);
			String g = x.getName();
			
			return x;	 // return the Company object
		}

		catch (Exception ex) {
			ex.printStackTrace();
			//return "Error Selecting Company: " + ex.toString();
			return null;
		}

	}

}
