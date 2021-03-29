package com.example.project.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired 
	private PatientRepository repo;
	
	public ResponseEntity<?> savePatient(Patient patient) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new HashMap<>();

		try {
			repo.save(patient);
		} catch (Exception e) {

			map.put("message", "Registration successful");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		}
		map.put("message", "Registration failure");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		List<Patient> ls = repo.findAll();
		return new ResponseEntity<List<Patient>>(ls,HttpStatus.OK);
	}

	public ResponseEntity<?> getPatientById(String id) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Patient>(repo.findById(id).get(),HttpStatus.OK);
	}

	public ResponseEntity<?> deletById(String id) {
		// TODO Auto-generated method stub
		Patient p = repo.findById(id).get();
		
		repo.delete(p);
		return new ResponseEntity<String>("patient deletd ",HttpStatus.OK);
	}
	
}
