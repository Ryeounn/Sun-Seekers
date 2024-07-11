var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/verify.xhtml') {
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

function togglelity() {
    var passwordField = document.getElementById("changeForm:newPassword");
    var showIcon = document.getElementById("showbIcon");
    var hideIcon = document.getElementById("hidebIcon");

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

function toggleConfirmVisibility() {
    var conpassword = document.getElementById("changeForm:conPassword");
    var conshowIcon = document.getElementById("conshowcIcon");
    var conhideIcon = document.getElementById("conhidecIcon");

    if (conpassword.type === "password") {
        conpassword.type = "text";
        conshowIcon.style.display = "none";
        conhideIcon.style.display = "block";
    } else {
        conpassword.type = "password";
        conshowIcon.style.display = "block";
        conhideIcon.style.display = "none";
    }
}

