function togglelity() {
    var passwordField = document.getElementById("registerForm:password");
    var showIcon = document.getElementById("showaIcon");
    var hideIcon = document.getElementById("hideaIcon");

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
    var conpassword = document.getElementById("registerForm:confirmRegister");
    var conshowIcon = document.getElementById("conshowIcon");
    var conhideIcon = document.getElementById("conhideIcon");

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
