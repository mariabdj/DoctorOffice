"use strict";

// Background custom by me from pen https://codepen.io/Mamboleoo/pen/BxMQYQ
var password = document.getElementById('password');
var toggleShowHide = document.querySelector('.show-hide-toggle');

function showHide() {
  if (password.type === 'password') {
    password.setAttribute('type', 'text');
    toggleShowHide.innerHTML = "Hide";
  } else {
    password.setAttribute('type', 'password');
    toggleShowHide.innerHTML = "Show";
  }
}