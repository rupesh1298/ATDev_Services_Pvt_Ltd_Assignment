package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class StudentController
 {
	@Autowired
	private StudentRepository empRepo;
	
	//get all Students
	@GetMapping("/Students")
	public List<Student> getAllEmp(){
		return empRepo.findAll();
	}
	
	@PostMapping("/Students")
	public Student addEmp(@RequestBody  Student emp) {
		return empRepo.save(emp);
	}
	
	@GetMapping("/Students/{id}")
	public ResponseEntity<Student> findById(@PathVariable Long id) {
		Student emp=empRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found"));
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/Students/{id}")
	public ResponseEntity<Student> updateEmp(@PathVariable Long id,@RequestBody Student StudentDetails){
	Student emp=empRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found"));
	//return ResponseEntity.ok(emp);
	
	emp.setFirstName(StudentDetails.getFirstName());
	emp.setLastName(StudentDetails.getLastName());
	emp.setEmailId(StudentDetails.getEmailId());
	
	Student updatedemp = empRepo.save(emp);
	return ResponseEntity.ok(updatedemp);
	
	}
	
	@DeleteMapping("/Students/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmploye(@PathVariable Long id)
	{
		Student emp=empRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found"));
		empRepo.delete(emp);
		Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted SuccessFully",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
}
