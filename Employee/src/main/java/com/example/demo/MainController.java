package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {

	@Autowired
	private EmployeeRepository employeeDao;

	// SQL Query Running Behind :
	// Insert INTO employee (name, companyId)
	// VALUES('user provided name', 'user provided id');

	@RequestMapping("/createemployee")
	@ResponseBody

	public String CreateEmployee(String name, long companyId) {

		try {
			Employee x = new Employee(name, companyId);
			employeeDao.save(x);
			return "Created New Employee" + x.toString();
		}

		catch (Exception ex) {
			return "Error Creating New Employee:" + ex.toString();
		}

	}
	// SQL Query Running Behind :
	// DELETE FROM employee
	// WHERE Id = user given Id;

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
	// UPDATE employee
	// SET NAME = NewName
	// WHERE ID = usergiven Id ;

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

	// SELECT * from Employee
	@RequestMapping("/selectallemployees")
	@ResponseBody
	public Iterable<Employee> getAllEmployees() {
		return employeeDao.findAll();

	}

	@Autowired
	private CompanyRepository companyDao;

	// SQL Query Running Behind :
	// Insert INTO company (name)
	// VALUES ('user providedname');

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
	// WHERE Id = user given Id;

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

	@RequestMapping("/deleteallcompany")
	@ResponseBody
	public String DeleteAllCompany(Long id) {

		try {
			companyDao.deleteAll(getAllCompanies());
			return "Deleted company" + id.toString();

		}

		catch (Exception ex) {
			return "Error creating new company:" + ex.toString();
		}
	}

	// SQL Query Running Behind :
	// UPDATE Company SET NAME = New Name
	// WHERE ID = user given Id ;

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
	// SELECT * from company
	@RequestMapping("/selectallcompany")
	@ResponseBody
	public Iterable<Company> getAllCompanies() {
		return companyDao.findAll();

	}

}
