package com.example.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.project.Model.Appointment;
import com.example.project.service.AppointmentService;

@RestController("/")
public class AppointmentController {
	
	@Autowired
	AppointmentService service;
	
	@PostMapping("appointment/register")
	public ResponseEntity<?> register(@RequestBody Appointment appointment){
		
		return service.register(appointment) ;
	}
	
	@GetMapping("appointment/list")
	public ResponseEntity<?> getAll(){
		return service.getAll();
	}
	
	@GetMapping("appointment/view/{appointmentId}")
	public ResponseEntity<?> getById(@PathVariable String appointmentId ){
		return service.getById(appointmentId);
	}
	
	
	
	@DeleteMapping("appointment/delete/{appointmentId}")
	public ResponseEntity<?> deletApp(@PathVariable String appointmentId){
		return service.deletApp(appointmentId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
