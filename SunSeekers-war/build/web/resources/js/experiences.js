AOS.init();

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
    const headerg = document.querySelector('.header-goodsay');
    const headerc = document.querySelector('.content-goodsay');
    const headerf = document.querySelector('.footer-goodsay');
    
    const signdining = document.querySelector('.signposting-rule-dining');
    
    const detaildining = document.querySelector('.detail-dining');
    
    const header_dining = document.querySelector('.heading-large-dining');
    const headingperfect = document.querySelector('.heading-large-perfect');
    const contentdining = document.querySelector('.content-dining');
    const contentperfect = document.querySelector('.content-perfect');
    
    const detailperfect = document.querySelector('.detail-perfect');
    const ctadriver = document.querySelector('.cta-divider');
    
    const button_expage = document.querySelector('.button-ex-page');
    
    const pexpage = document.querySelector('.js-text');

    if (isInViewport(headerg)) {
        headerg.classList.add('visible');
        headerc.classList.add('visible');
        headerf.classList.add('visible');
    }
    
    if(isInViewport(signdining)){
        signdining.classList.add('visible');
        detaildining.classList.add('visible');
        header_dining.classList.add('visible');
        contentdining.classList.add('visible');
    }
    
    if(isInViewport(button_expage)){
        button_expage.classList.add('visible');
    }
    
    if(isInViewport(detailperfect)){
        detailperfect.classList.add('visible');
        ctadriver.classList.add('visible');
        headingperfect.classList.add('visible');
        contentperfect.classList.add('visible');
    }
}

window.addEventListener('scroll', handleScroll);


