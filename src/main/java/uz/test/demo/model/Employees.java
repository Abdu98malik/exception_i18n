package uz.test.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Employees {

	private List<Employee> employeeList;
	
	public List<Employee> getEmployeeList(){
		if (employeeList == null) {
			employeeList = new ArrayList<>();
		}
		
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public int size() {
		if (employeeList.size() == 0) {
			return 0;
		}
		return 1;
	}
}
