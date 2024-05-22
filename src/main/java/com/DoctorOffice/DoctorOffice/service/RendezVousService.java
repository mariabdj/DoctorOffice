package com.DoctorOffice.DoctorOffice.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.Medecin;
import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.RendezVous;
import com.DoctorOffice.DoctorOffice.repository.RendezVousRepository;

@Service
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final MedecinService medecinService;
    private final PatientService patientService;  // Assume there's a service to get Patient


    @Autowired
    public RendezVousService(RendezVousRepository rendezVousRepository, MedecinService medecinService, PatientService patientService) {
        this.rendezVousRepository = rendezVousRepository;
        this.medecinService = medecinService;
        this.patientService = patientService;
    }

    public void addAppointment(Date dateRen, String heure, String medecinId, Patient patient) {
        System.out.println("Adding appointment...");
        // Retrieve Medecin information using the medecinId
        Medecin medecin = medecinService.getMedecinById(medecinId); 
        if (medecin != null) {
            System.out.println("Medecin found: " + medecin.getNom() + " " + medecin.getPrenom());
            // Create a new appointment instance
            RendezVous rendezVous = new RendezVous(dateRen, heure, patient, medecin);
            // Save the appointment to the database
            rendezVousRepository.save(rendezVous);
            System.out.println("Appointment saved!");
        } else {
            System.out.println("Medecin not found!");
        }
    }

    public Map<Date, List<RendezVous>> getAppointmentsByPatientAndDate(Patient patient) {
        List<RendezVous> appointments = rendezVousRepository.findByPatient(patient);
        return appointments.stream()
                .collect(Collectors.groupingBy(RendezVous::getDateRen));
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public Map<String, List<RendezVous>> getAppointmentsByPatientAndMonth(Long patientId, int year, int month) {
        Patient patient = patientService.getPatientById(patientId);
    
        // Set the start time to the beginning of the first day of the month
        Calendar startCal = Calendar.getInstance();
        startCal.set(year, month - 1, 1, 0, 0, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date startDate = startCal.getTime();
    
        // Set the end time to the end of the last day of the month
        Calendar endCal = Calendar.getInstance();
        endCal.set(year, month - 1, startCal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        endCal.set(Calendar.MILLISECOND, 999); // Make sure the end date is inclusive
        Date endDate = endCal.getTime();
    
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
    
        List<RendezVous> appointments = rendezVousRepository.findByPatientAndDateRenBetween(patient, startDate, endDate);
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
        return appointments.stream().collect(Collectors.groupingBy(appt -> dateFormat.format(appt.getDateRen())));
    }
    

    public List<RendezVous> getAppointmentsByMonth(int year, int month) {
    LocalDate startOfMonth = LocalDate.of(year, month, 1);
    LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());
    return rendezVousRepository.findByDateRenBetween(
        Date.from(startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant()), // Convert LocalDate to Date
        Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant())
    );
}

    
}
