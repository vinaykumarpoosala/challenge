package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.Model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,String>{

	List<Appointment> findByPatientId();

}
