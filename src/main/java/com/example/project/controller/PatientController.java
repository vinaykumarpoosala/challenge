package com.example.project.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;
@RestController("/")
public class PatientController {

	@Autowired
	PatientService service;
	
	@PostMapping("patients/register")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient){
		return service.savePatient(patient);
	}
	
	@GetMapping("patients/list")
	public ResponseEntity<?> patientsList(){
		return service.getAll();
	}
	
	@GetMapping("patients/view/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable String id){
		
		return service.getPatientById(id);
	}
	
	
	@DeleteMapping("patients/delete/{id}")
	public ResponseEntity<?> deletePatientById(@PathVariable String id){
		return service.deletById(id);
	}

}
