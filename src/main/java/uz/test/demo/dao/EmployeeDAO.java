package uz.test.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import uz.test.demo.model.Employee;
import uz.test.demo.model.Employees;

@Repository
public class EmployeeDAO {

	private static List<Employee> list = new ArrayList<>();
	
	
	static {
		//list.add(new Employee(1,"Abdulmalik", "Mamirov","abdu98malik@mail.ru"));

		
	}
	
	public List<Employee> getAllEmployees() 
    {
        return list;
    }
     
  
}
