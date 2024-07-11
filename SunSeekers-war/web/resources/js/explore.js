AOS.init();
var swiper = new Swiper(".mySwiper6", {
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
    const signpostel = document.querySelector('.signposting-rule-ex-pl');
    const signposbest = document.querySelector('.signposting-rule-best');

    const detailel = document.querySelector('.detail-ex-pl');
    const detailbest = document.querySelector('.detail-best');
    
    const headerex = document.querySelector('.header-goodsay-ex');
    const contentex = document.querySelector('.content-goodsay-ex');
    const footerex = document.querySelector('.footer-goodsay-ex');
    
    if(isInViewport(signposbest)){
        signposbest.classList.add('visible');
        detailbest.classList.add('visible');
    }

    if (isInViewport(signpostel)) {
        signpostel.classList.add('visible');
        detailel.classList.add('visible');
    }
    
    if(isInViewport(headerex)){
        headerex.classList.add('visible');
        contentex.classList.add('visible');
        footerex.classList.add('visible');
    }
}

window.addEventListener('scroll', handleScroll);

