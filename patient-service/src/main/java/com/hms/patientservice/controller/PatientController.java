package com.hms.patientservice.controller;

import com.hms.patientservice.entity.Patient;
import com.hms.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/test")
    public String test() {
        return "Patient Service Running";
    }

    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id);
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable Long id,
                                 @RequestBody Patient updatedPatient) {

        Patient patient = patientRepository.findById(id).orElseThrow();

        patient.setName(updatedPatient.getName());
        patient.setAge(updatedPatient.getAge());
        patient.setDisease(updatedPatient.getDisease());

        return patientRepository.save(patient);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {

        patientRepository.deleteById(id);

        return "Patient Deleted Successfully";
    }
}