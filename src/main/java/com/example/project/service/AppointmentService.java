package com.example.project.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.Model.Appointment;
import com.example.project.Model.Patient;
import com.example.project.repository.AppointmentRepository;
import com.example.project.repository.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository repo;

	public ResponseEntity<?> register(Appointment appointment) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();

		try {
			repo.save(appointment);
		} catch (Exception e) {

			map.put("message", "Booking successful");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		}
		map.put("message", "Booking failure");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		List<Appointment> ls = repo.findAll();
		return new ResponseEntity<List<Appointment>>(ls, HttpStatus.OK);
	}

	public ResponseEntity<?> getById(String appointmentId) {
		// TODO Auto-generated method stub

		return new ResponseEntity<Appointment>(repo.findById(appointmentId).get(), HttpStatus.OK);

	}

	public ResponseEntity<?> deletApp(String appointmentId) {
		// TODO Auto-generated method stub

		Appointment ap = repo.findById(appointmentId).get();
		repo.delete(ap);
		return ResponseEntity.ok("appointment deleted");
	}

	public ResponseEntity<?> listByPatientId(String patientId) {
		// TODO Auto-generated method stub
		List<Appointment> li = repo.findByPatientId();
		return new ResponseEntity<List<Appointment>>(li, HttpStatus.OK);
	}

}
