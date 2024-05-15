"use strict";

$('.email').on("change keyup paste", function () {
  if ($(this).val()) {
    $('.icon-paper-plane').addClass("next");
  } else {
    $('.icon-paper-plane').removeClass("next");
  }
});
$('.next-button.email').click(function () {
  var selectedDate = new Date($('.email').val());
  var today = new Date();
  today.setHours(0, 0, 0, 0); // Check if date is valid

  if (!selectedDate || selectedDate <= today) {
    return; // Prevent proceeding to the next input
  }

  $('.email-section').addClass("fold-up");
  $('.password-section').removeClass("folded");
});
$('.password').on("change keyup paste", function () {
  if ($(this).val()) {
    $('.icon-lock').addClass("next");
  } else {
    $('.icon-lock').removeClass("next");
  }
});
$('.next-button.password').click(function () {
  var selectedTime = $('.password').val().split(':');
  var hour = parseInt(selectedTime[0]); // Check if hour is valid

  if (hour < 8 || hour > 16) {
    return; // Prevent proceeding to the next input
  }

  $('.password-section').addClass("fold-up");
  $('.repeat-password-section').removeClass("folded");
});
$('.repeat-password').on("change keyup paste", function () {
  if ($(this).val()) {
    $('.icon-repeat-lock').addClass("next");
  } else {
    $('.icon-repeat-lock').removeClass("next");
  }
});
$('.next-button.repeat-password').click(function () {
  // Check if doctor is selected
  if (!$('.repeat-password').val()) {
    return; // Prevent proceeding to the next input
  }

  $('.repeat-password-section').addClass("fold-up");
  $('.success').css("marginTop", 0);
});