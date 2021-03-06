package com.example.demo.controllers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeListRepository;
import com.example.demo.models.EmployeeRepository;


@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {
	//ArrayList<Employee> data = new ArrayList<Employee>();
	EmployeeRepository employees= new EmployeeListRepository();
	
	@GetMapping(path="/hello")
	@ResponseBody
	public String hello()
	{
		return "Hello world!";
	}
	@RequestMapping(path="/")
	public String index(Model model)
	{
		return "/static/index.html";
	}
	
	@GetMapping(path="/testdata")
	@ResponseBody
	public String test_data()
	{	
		if(employees.count() == 0)
		{
			employees.save(new Employee(0, "Vasya","Pupkin",LocalDate.of(1990, 1, 1), 900));
			employees.save(new Employee(0, "Fedya","Losev",LocalDate.of(1985, 10, 15), 500));
			employees.save(new Employee(0, "Masha","Repkina",LocalDate.of(1990, 5, 17), 1900));
		}
		return "Test data added";
	}
	
	@GetMapping(path="/listjson")
	@ResponseBody
	public Iterable<Employee> get_employees()
	{
		return employees.findAll();
	}
	
	@GetMapping(path="/list")
	public String get_employee_table(Model model)
	{
		model.addAttribute("employees", employees.findAll());
		return "employeelist";
	}
	
	@GetMapping(path="/listxml", produces="application/xml")
	@ResponseBody
	public String get_employees_xml()
	{
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		xml+="<Employees>\n";
		for (Employee e : employees.findAll())
		{
			xml+="\t<Employee ID=\""+e.getID()+"\">\n";
			xml+="\t\t<FirstName>\""+e.getFirstName()+"\"</FirstName>\n";
			xml+="\t\t<LastName>\""+e.getLastName()+"\"</LastName>\n";
			xml+="\t\t<Birthday>\""+e.getBirthday()+"\"</Birthday>\n";
			xml+="\t\t<Salary>\""+e.getSalary()+"\"</Salary>\n";
			xml+="\t</Employee>";
		}
		xml+="</Employees>";
		return xml;
	}
	
	@GetMapping(path="/getEmployee")
	@ResponseBody
	public Employee getEmployee(@RequestParam int ID)
	{
		Employee found = employees.findById(ID).get();
		if(found == null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"");
		}
		
		return found;
	}
	
	@GetMapping(path="/addEmployee")
	@ResponseBody
	public String addEmployee(@RequestParam int ID, @RequestParam String FirstName, @RequestParam String LastName, 
								@RequestParam String Birthday, @RequestParam double Salary)
	{ 
		LocalDate birthday = LocalDate.parse(Birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		employees.save(new Employee(ID, FirstName,LastName,birthday,Salary));
		
		return "New user added";
	}
	
	
}
