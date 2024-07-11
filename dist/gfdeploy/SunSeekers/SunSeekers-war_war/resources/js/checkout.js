var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/checkout.xhtml') {
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
;

function goToStep(stepId) {
    document.querySelectorAll('.step').forEach(s => s.classList.remove('active', 'enabled'));
    document.querySelectorAll('.input-checkout').forEach(element => element.classList.remove('active'));

    if (stepId === 'payment-step') {
        document.getElementById('payment-detail').classList.add('active');
    } else if (stepId === 'review-step') {
        document.getElementById('review-order').classList.add('active');
        document.getElementById('traveler-step').classList.add('disabled');  // Disable traveler step
        document.getElementById('payment-step').classList.add('disabled');   // Disable payment step
    } else {
        stepId = 'traveler-step';
        document.getElementById('traveler-detail').classList.add('active');
    }

    document.getElementById(stepId).classList.add('active', 'enabled');
}

function goToNextStep(data) {
    if (data.status === "success") {
        var activeStep = document.querySelector('.step.active');
        var nextStep = activeStep.nextElementSibling;
        if (nextStep) {
            goToStep(nextStep.id);
        }
    }
}

function goToPreviousStep(data) {
    if (data.status === "success") {
        var activeStep = document.querySelector('.step.active');
        var prevStep = activeStep.previousElementSibling;
        if (prevStep) {
            goToStep(prevStep.id);
        }
    }
}

document.querySelectorAll('.step').forEach(step => {
    step.addEventListener('click', function () {
        if (this.classList.contains('enabled')) {
            goToStep(this.id);
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    goToStep('traveler-step');
});

function scrollToTop() {
    window.scrollTo(0, 0);
}