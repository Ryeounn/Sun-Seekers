AOS.init();

document.addEventListener("DOMContentLoaded", function () {
    var loadingOverlay = document.getElementById("loading-start");
    var logoHome = document.getElementById('logo-home');
    var underlineLeft = document.querySelector('.underline-left');
    var underlineRight = document.querySelector('.underline-right');
    var mainUnderline = document.getElementById('underline');
    var nameOverlay = document.querySelector(".name-overlay");

    nameOverlay.style.opacity = 1;
    nameOverlay.style.transform = 'translateY(0)';
    nameOverlay.style.transition = "all 1s ease";

    logoHome.style.opacity = 1;
    logoHome.style.transform = 'translateY(-10px)';
    logoHome.style.transition = 'all 1s ease';

    underlineLeft.style.width = '40px';
    underlineLeft.style.transform = 'translateY(0)';
    underlineLeft.style.transition = 'all 1s ease';

    underlineRight.style.width = '40px';
    underlineRight.style.transform = 'translateY(0)';
    underlineRight.style.transition = 'all 1s ease';

    mainUnderline.style.width = '80px';
    setTimeout(function () {
        loadingOverlay.classList.add("move-up");
    }, 4000);
});

window.onload = loading();

function loading() {
    var loadingOverlay = document.getElementById("loading-start");
    var logoHome = document.getElementById('logo-home');
    var underlineLeft = document.querySelector('.underline-left');
    var underlineRight = document.querySelector('.underline-right');
    var mainUnderline = document.getElementById('underline');
    var nameOverlay = document.querySelector(".name-overlay");

    nameOverlay.style.opacity = 1;
    nameOverlay.style.transform = 'translateY(0)';
    nameOverlay.style.transition = "all 1s ease";

    logoHome.style.opacity = 1;
    logoHome.style.transform = 'translateY(-10px)';
    logoHome.style.transition = 'all 1s ease';

    underlineLeft.style.width = '40px';
    underlineLeft.style.transform = 'translateY(0)';
    underlineLeft.style.transition = 'all 1s ease';

    underlineRight.style.width = '40px';
    underlineRight.style.transform = 'translateY(0)';
    underlineRight.style.transition = 'all 1s ease';

    mainUnderline.style.width = '80px';
    setTimeout(function () {
        loadingOverlay.classList.add("move-up");
    }, 1000);
    window.scrollTo(0, 0);
};

document.addEventListener('DOMContentLoaded', function () {
    var treeLink = document.querySelector('.scroll-home');
    var scrollEx = document.getElementById('scroll-ex');
    var intro = document.getElementById('intro');
    var exer = document.getElementById('explore');

    treeLink.addEventListener('click', function (event) {
        event.preventDefault();

        var introPosition = intro.getBoundingClientRect().top;

        window.scroll({
            top: introPosition,
            behavior: 'smooth'
        });
    });

//    scrollEx.addEventListener('click', function (event) {
//        event.preventDefault();
//
//        var ex = exer.getBoundingClientRect().top;
//
//        window.scroll({
//            top: ex,
//            behavior: 'smooth'
//        });
//    });
});


window.onload = function () {
    var video = document.getElementById('headerVideo_video');
    video.play();
};

function isInViewport(element) {
    const rect = element.getBoundingClientRect();
    return (
            rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
            );
}

function handleScroll() {
    const signposting = document.querySelector('.signposting-rule');
    const signtour = document.querySelector('.signposting-rule-tour');
    const signgas = document.querySelector('.signposting-rule-gastronomy');
    const signexperi = document.querySelector('.signposting-rule-experiences');
    const signlocation = document.querySelector('.signposting-rule-location');
    const signbook = document.querySelector('.signposting-rule-booking');

    const detail = document.querySelector('.detail');
    const detailtour = document.querySelector('.detail-tour');
    const detailgas = document.querySelector('.detail-gastronomy');
    const detailexperi = document.querySelector('.detail-experiences');
    const detaillocation = document.querySelector('.detail-location');
    const detailbook = document.querySelector('.detail-booking');

    const header_large = document.querySelector('.heading-large');
    const header_gastronomy = document.querySelector('.heading-large-gastronomy');
    const header_experiences = document.querySelector('.heading-large-experiences');
    const header_location = document.querySelector('.heading-large-location');
    const header_booking = document.querySelector('.heading-large-booking');
    const gas_experience = document.querySelector('.gastronomy-experience');
    const des_ex = document.querySelector('.description-ex');
    const des_experiences = document.querySelector('.description-experiences');

    const button = document.querySelector('.button');
    const button_ex = document.querySelector('.button-ex');
    const button_experiences = document.querySelector('.button-experiences');
    const button_location = document.querySelector('.button-location');
    const button_booking = document.querySelector('.button-booking');

    const headerg = document.querySelector('.header-goodsay');
    const headerc = document.querySelector('.content-goodsay');
    const headerf = document.querySelector('.footer-goodsay');

    const loadd = document.querySelector('.location-address');
    const lotel = document.querySelector('.location-tel');
    const lomail = document.querySelector('.location-mail');

    const booksub = document.querySelector('.booking-sub');
    const bookwhy = document.querySelector('.why-book-direct');

    const pexpage = document.querySelector('.content-in-video > p');

    if (isInViewport(signposting)) {
        signposting.classList.add('visible');
        detail.classList.add('visible');
        header_large.classList.add('visible');
        des_ex.classList.add('visible');
        button.classList.add('visible');
    }

    if (isInViewport(signtour)) {
        signtour.classList.add('visible');
        detailtour.classList.add('visible');
    }

    if (isInViewport(signgas)) {
        signgas.classList.add('visible');
        detailgas.classList.add('visible');
        header_gastronomy.classList.add('visible');
        gas_experience.classList.add('visible');
        button_ex.classList.add('visible');
    }

    if (isInViewport(signexperi)) {
        signexperi.classList.add('visible');
        detailexperi.classList.add('visible');
        header_experiences.classList.add('visible');
        des_experiences.classList.add('visible');
        button_experiences.classList.add('visible');
    }

    if (isInViewport(headerg)) {
        headerg.classList.add('visible');
        headerc.classList.add('visible');
        headerf.classList.add('visible');
    }

    if (isInViewport(signlocation)) {
        signlocation.classList.add('visible');
        detaillocation.classList.add('visible');
        header_location.classList.add('visible');
        loadd.classList.add('visible');
        lotel.classList.add('visible');
        lomail.classList.add('visible');
        button_location.classList.add('visible');
    }

    if (isInViewport(signbook)) {
        signbook.classList.add('visible');
        detailbook.classList.add('visible');
        header_booking.classList.add('visible');
        booksub.classList.add('visible');
        button_booking.classList.add('visible');
        bookwhy.classList.add('visible');
    }
}

window.addEventListener('scroll', handleScroll);

document.addEventListener("DOMContentLoaded", function () {
    const container = document.querySelector('.swiper-container');
    const wrapper = document.querySelector('.swiper-wrapper');
    const slides = document.querySelectorAll('.swiper-slide');
    const prevButton = document.querySelector('.swiper-button-prev');
    const nextButton = document.querySelector('.swiper-button-next');
    const slideWidth = slides[0].offsetWidth;
    const slideMargin = 10;
    const slidesPerView = 3;
    let currentIndex = 0;

    function goToSlide(index) {
        if (index < 0) {
            index = slides.length - slidesPerView;
        } else if (index >= slides.length - slidesPerView + 1) {
            index = 0;
        }
        wrapper.style.transform = `translateX(-${(index * (slideWidth + slideMargin))}px)`;
        currentIndex = index;
    }

    prevButton.addEventListener('click', function () {
        goToSlide(currentIndex - 1);
    });

    nextButton.addEventListener('click', function () {
        goToSlide(currentIndex + 1);
    });
});

var element = document.getElementsByClassName("col-umn");
var i;

function four() {
    var elements = document.querySelectorAll('.rows > .col-umn');
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.width = '100%';
        elements[i].style.padding = '4px';
        elements[i].style.boxSizing = 'border-box';
    }
}

document.addEventListener("DOMContentLoaded", function () {
    var loadingOverlay = document.getElementById("loading-start");
    setTimeout(function () {
        loadingOverlay.classList.add("move-up");
    }, 1000);
});

var swiper = new Swiper(".mySwiper5", {
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

