var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/userInfo.xhtml') {
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

function showSection(sectionId) {
    scrollToTop();
    
    const sections = document.querySelectorAll('.info-box');
    sections.forEach(section => section.style.display = 'none');

    document.getElementById(sectionId).style.display = 'block';

    const links = document.querySelectorAll('.nav-userinfo a');
    links.forEach(link => link.classList.remove('actived'));

    const activeLink = document.querySelector(`.nav-userinfo a[onclick="showSection('${sectionId}')"]`);
    activeLink.classList.add('actived');
}

function togglePasswordVisibility() {
    var passwordField = document.getElementById("changepassFrom:user-newPass");
    var confirmField = document.getElementById("changepassFrom:user-confirmPass");
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

function toggleConfirmPasswordVisibility() {
    var confirmField = document.getElementById("changepassFrom:user-confirmPass");
    var showIcon = document.getElementById("showConfirmPassIcon");
    var hideIcon = document.getElementById("hideConfirmPassIcon");

    if (confirmField.type === "password") {
        confirmField.type = "text";
        showIcon.style.display = "none";
        hideIcon.style.display = "block";
    } else {
        confirmField.type = "password";
        showIcon.style.display = "block";
        hideIcon.style.display = "none";
    }
}

window.onload = function () {
    var emailStatus = localStorage.getItem('emailStatus');
    if (emailStatus) {
        updateUI(emailStatus === 'true');
    }
};

function toggleEmail() {
    var toggleSwitch = document.getElementById('toggleSwitch');
    var currentStatus = toggleSwitch.classList.contains('isToggleOn');
    var newStatus = !currentStatus;

    // Cập nhật localStorage
    localStorage.setItem('emailStatus', newStatus);

    // Cập nhật giao diện
    updateUI(newStatus);
}

function updateUI(isOn) {
    var toggleSwitch = document.getElementById('toggleSwitch');
    var emailStatus = document.getElementById('emailStatus');
    var yes1 = document.getElementById('yes-reci');

    if (isOn) {
        toggleSwitch.classList.add('isToggleOn');
        emailStatus.innerText = 'Receive';
        yes1.innerText = 'Yes';
        
    } else {
        toggleSwitch.classList.remove('isToggleOn');
        emailStatus.innerText = 'No';
        yes1.innerText = 'No';
    }
}

window.onload = function () {
    var emailStatus2 = localStorage.getItem('emailStatus2');
    if (emailStatus2) {
        updateUI(emailStatus2 === 'true');
    }
};

function toggleEmail2() {
    var toggleSwitch2 = document.getElementById('toggleSwitch2');
    var currentStatus2 = toggleSwitch2.classList.contains('isToggleOn');
    var newStatus2 = !currentStatus2;

    // Cập nhật localStorage
    localStorage.setItem('emailStatus2', newStatus2);

    // Cập nhật giao diện
    updateUI2(newStatus2);
}

function updateUI2(isOn) {
    var toggleSwitch2 = document.getElementById('toggleSwitch2');
    var emailStatus2 = document.getElementById('emailStatus2');
    var yes2 = document.getElementById('yes-reci2');

    if (isOn) {
        toggleSwitch2.classList.add('isToggleOn');
        emailStatus2.innerText = 'Receive';
        yes2.innerText = 'Yes';
        
    } else {
        toggleSwitch2.classList.remove('isToggleOn');
        emailStatus2.innerText = 'No';
        yes2.innerText = 'No';
    }
}

function scrollToTop() {
    window.scrollTo(0, 0);
}