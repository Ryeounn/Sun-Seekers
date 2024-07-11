var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/change.xhtml') {
    var header = document.getElementById('header');
    var nav = document.querySelectorAll('#nav li a');
    var logingr = document.querySelectorAll('.login-group a');
    header.style.background = "#fff";
    nav.forEach(element => {
        element.style.color = '#383838';
    });
    logingr.forEach(element => {
        element.style.color = '#383838';
    });
}

function togglePasswordVisibility() {
    var passwordField = document.getElementById("loginForm:password");
    var showIcon = document.getElementById("showPassIcon");
    var hideIcon = document.getElementById("hidePassIcon");

    if (passwordField.type === "password") {
        passwordField.type = "text";
        showIcon.style.display = "none";
        hideIcon.style.display = "block";
    } else {
        passwordField.type = "password";
        showIcon.style.display = "block";
        hideIcon.style.display = "none";
    }
}

const emailInput = document.getElementById('productForm:username');
const passwordInput = document.getElementById('loginForm:password');
const signInButton = document.getElementById('loginForm:account-login-button');

function checkInputs() {
    const emailValue = emailInput.value.trim();
    const passwordValue = passwordInput.value.trim();

    if (emailValue !== "" && passwordValue !== "") {
        signInButton.disabled = false;
    } else {
        signInButton.disabled = true;
    }
}

document.addEventListener('DOMContentLoaded', function() {
    initializeLoginPage();
    jsf.ajax.addOnEvent(function(data) {
        if (data.status === 'success') {
            initializeLoginPage();
        }
    });
});

