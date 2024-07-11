var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/cart.xhtml') {
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

var swiper = new Swiper(".mySwiper4", {
    slidesPerView: 3,
    spaceBetween: 25,
    loop: false,
    centerSlide: 'true',
    fade: 'true',
    grabCursor: 'true',
    loopFillGroupWithBlank: true,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
        dynamicBullets: true
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
    },
    breakpoints: {
        0: {
            slidesPerView: 1,
        },
        520: {
            slidesPerView: 3,
        },
        950: {
            slidesPerView: 4,
        }
    }
});
console.log(swiper);
swiper.on('slideChangeTransitionEnd', function () {
    var currentIndex = swiper.activeIndex;
    var slidesPerView = swiper.params.slidesPerView;
    console.log(currentIndex);
    console.log(slidesPerView);

    // Ẩn hoặc hiện nút prev tùy theo index
    if (currentIndex <= 0 || currentIndex == "") {
        document.querySelector('.grid-more .swiper-button-prev').style.visibility = 'hidden';
        document.querySelector('.grid-more .swiper-button-prev').style.opacity = '0';
    } else {
        document.querySelector('.grid-more .swiper-button-prev').style.visibility = 'visible';
        document.querySelector('.grid-more .swiper-button-prev').style.opacity = '1';
        document.querySelector('.grid-more .swiper-button-prev').style.display = 'block';
        document.querySelector('.grid-more .swiper-button-prev').style.paddingTop = '27%';
    }
    // Ẩn hoặc hiện nút next tùy theo index
    if (currentIndex >= swiper.slides.length - slidesPerView) {
        document.querySelector('.grid-more .swiper-button-next').style.visibility = 'hidden';
        document.querySelector('.grid-more .swiper-button-next').style.opacity = '0';
    } else {
        document.querySelector('.grid-more .swiper-button-next').style.visibility = 'visible';
        document.querySelector('.grid-more .swiper-button-next').style.opacity = '1';
    }
});

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


