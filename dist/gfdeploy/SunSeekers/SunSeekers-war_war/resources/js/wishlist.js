var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/wishlist.xhtml') {
    var header = document.getElementById('header');
    var nav = document.querySelectorAll('#nav li a');
    var logingr = document.querySelectorAll('.login-group a');
    var span = document.querySelector('.signin_class span');
    var logig = document.querySelector('.btn-outline-light');
    header.style.background = "#fff";
    nav.forEach(element => {
        console.log("ab");
        element.style.color = '#383838';
    });
    logingr.forEach(element => {
        console.log("ac");
        element.style.color = '#383838';
    });
    span.style.color = '#383838';
    logig.style.border = '1px solid #383838';
};

var modal = document.getElementById('myModal');
var input = document.querySelector('.input');
var modaler = document.getElementById('myModalMobile');

function openModal() {
    modal.style.opacity = '1';
    modal.style.zIndex = '1';
    modal.style.visibility = 'visible';
    modal.querySelector('.modal-content').style.transform = 'translateY(0)';
}

function closeModal(event) {
    if (event.target === modal) {
        modal.style.opacity = '0';
        modal.style.visibility = 'hidden';
        modal.querySelector('.modal-content').style.transform = 'translateY(-20px)';
    }

}

document.addEventListener("DOMContentLoaded", function () {
    var openModal = document.getElementById("openModalLogin");
    var modal = document.getElementById("myModalLogin");
    var overlay = document.getElementById("overlayLogin");
    var closeBtn = modal.querySelector(".close");

    openModal.addEventListener("click", function (event) {
        event.preventDefault();
        modal.classList.add("active");
        overlay.classList.add("active");
    });

    closeBtn.addEventListener("click", function () {
        modal.classList.remove("active");
        overlay.classList.remove("active");
    });

    overlay.addEventListener("click", function () {
        modal.classList.remove("active");
        overlay.classList.remove("active");
    });
});

