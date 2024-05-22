package com.DoctorOffice.DoctorOffice.controller.Doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PatientList {

    private final PatientService patientService;

    @Autowired
    public PatientList(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/redirectPatientList")
    public String PatientShow(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Patient> patients;
        if (name != null && !name.isEmpty()) {
            patients = patientService.searchPatientsByName(name);
        } else {
            patients = patientService.getAllPatients();
        }
        model.addAttribute("patients", patients);
        System.out.println("Number of patients passed to the view: " + patients.size());
        return "Doctor/PatientList";
    }

    @PostMapping("/redirect")
    public String redirectMedicalFile(@RequestParam("patientId") Long patientId, HttpSession session) {
        Patient patient = patientService.getPatientById(patientId);
        session.setAttribute("patient", patient);
        return "redirect:/redirectMedicalFile"; 
}
}
