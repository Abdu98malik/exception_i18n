package uz.test.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uz.test.demo.dao.EmployeeDAO;
import uz.test.demo.errorresponse.EmployeeErrorResponse;
import uz.test.demo.errorresponse.EmployeeNotFoundException;
import uz.test.demo.model.Employee;
import uz.test.demo.model.Employees;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	public MessageSource messageSource;
	
	@GetMapping(path="/", produces = "application/json")
    public List<Employee> getEmployees(Locale locale) 
    {
		if ( employeeDao.getAllEmployees().size() == 0 ) {
			
			throw new EmployeeNotFoundException(messageSource.getMessage("error.notFound",null, locale));
		}
        return employeeDao.getAllEmployees();
        
        //messageSource.getMessage("error.notFound",null, locale);
    }
     
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc) {
		
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc) {
		
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}	
    
}
