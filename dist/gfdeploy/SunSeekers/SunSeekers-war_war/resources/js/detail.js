var swiper = new Swiper(".slide-content-detail", {
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

$(document).ready(function () {
    $(".div-sname").click(function () {
        $(this).closest(".box-schedule").find(".box-schedule-content").slideToggle();
    });
});

var swiper2 = new Swiper(".slide-content-detail", {
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
        dynamicBullets: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
    },
    breakpoints: {
        0: {
            slidesPerView: 1
        },
        520: {
            slidesPerView: 3
        },
        950: {
            slidesPerView: 4
        }
    }
});

function handleAjaxComplete(data) {
        if (data.status === "success") {
            openAddModal();
        }
    }

    function openAddModal() {
        var modal = document.getElementById("addSuccessModal");
        var modalContent = modal.querySelector('.add-modal-content');
        var overlay = document.getElementById("addSuccessModalOverlay");
        modal.classList.add("show");
        overlay.classList.add("show");
        setTimeout(function() {
            modal.style.opacity = "1";
            modalContent.style.right = "0"; // Ensure it slides in
            modalContent.style.transform = 'translateX(0) translateY(-50%)';
            overlay.style.opacity = "1";
        }, 10); // Slight delay to ensure display:block takes effect before opacity transition
    }

    function closeAddModal() {
        var modal = document.getElementById("addSuccessModal");
        var modalContent = modal.querySelector('.add-modal-content');
        var overlay = document.getElementById("addSuccessModalOverlay");
        modal.style.opacity = "0";
        overlay.style.opacity = "0";
        modalContent.style.right = '-100%';
        setTimeout(function() {
            modal.classList.remove("show");
            overlay.classList.remove("show");
            modalContent.style.transform = 'translateX(0) translateY(-50%)'; // Reset transform for next open
        }, 500); // Match this delay with the CSS transition duration
    }