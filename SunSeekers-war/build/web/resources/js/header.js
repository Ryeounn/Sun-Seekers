window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 125 || document.documentElement.scrollTop > 125) {
        document.getElementById("header").classList.add("header-scroll");
    } else {
        document.getElementById("header").classList.remove("header-scroll");
    }
};

var modal = document.getElementById('myModal');
var input = document.querySelector('.input');
var modaler = document.getElementById('myModalMobile');

function openModal() {
    modal.style.opacity = '1';
    modal.style.zIndex = '1';
    modal.style.visibility = 'visible';
    modal.querySelector('.modal-content-header').style.transform = 'translateY(0)';
}

function closeModal(event) {
    if (event.target === modal) {
        modal.style.opacity = '0';
        modal.style.visibility = 'hidden';
        modal.querySelector('.modal-content-header').style.transform = 'translateY(-20px)';
    }
}

document.addEventListener("DOMContentLoaded", function () {
    var openModal = document.getElementById("openModalLoginHeader");
    var modal = document.getElementById("myModalLoginHeader");
    var overlay = document.getElementById("overlayLogin");
    var closeBtn = document.querySelector(".close");

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