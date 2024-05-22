"use strict";

$(document).ready(function () {
  // Hide both forms initially
  $('#ajoutPat').hide();
  $('#ajoutConsultation').hide(); // Add Patient button click event

  $('#addPat').click(function () {
    $('#ajoutPat').show();
    $('#ajoutConsultation').hide();
    $('#contentContainer').hide();
  }); // Add Consultation button click event

  $('#addCons').click(function () {
    $('#ajoutPat').hide();
    $('#ajoutConsultation').show();
    $('#contentContainer').hide();
  }); // Add Appointment button click event

  $('#addAppointement').click(function (e) {
    e.preventDefault();
    var url = $(this).attr('href');
    $('#ajoutPat').hide();
    $('#ajoutConsultation').hide();
    $('#contentContainer').show();
    $.ajax({
      url: url,
      type: 'GET',
      async: true,
      success: function success(data) {
        $('#contentContainer').html(data); // Load response into content container
      },
      error: function error(xhr, status, _error) {
        console.error(xhr.responseText);
      }
    });
  });
});