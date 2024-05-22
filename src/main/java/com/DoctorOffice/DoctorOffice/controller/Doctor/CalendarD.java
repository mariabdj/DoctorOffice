package com.DoctorOffice.DoctorOffice.controller.Doctor;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DoctorOffice.DoctorOffice.entity.RendezVous;
import com.DoctorOffice.DoctorOffice.service.RendezVousService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalendarD {

    private final RendezVousService rendezVousService;

    @Autowired
    public CalendarD(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @GetMapping("/redirectCalendarD")
public String calendar(Model model, HttpSession session) {
    return "Doctor/CalendarD";
}

@GetMapping("/getAppointments")
@ResponseBody
public Map<String, List<RendezVous>> getAppointmentsByMonth(@RequestParam int year, @RequestParam int month) {
    List<RendezVous> appointments = rendezVousService.getAppointmentsByMonth(year, month);
    
    // Create a map to organize appointments by date
    Map<String, List<RendezVous>> appointmentsMap = new HashMap<>();
    for (RendezVous appointment : appointments) {
        String dateKey = formatDateKey(appointment.getDateRen()); // Format date key
        appointmentsMap.computeIfAbsent(dateKey, k -> new ArrayList<>()).add(appointment);
    }
    
    return appointmentsMap;
}

// Helper method to format date key as "YYYY-MM-DD"
private String formatDateKey(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
}



}