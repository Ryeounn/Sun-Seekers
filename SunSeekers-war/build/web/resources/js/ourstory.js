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
    const signhotel = document.querySelector('.signposting-rule-hotel');
    const signpeople = document.querySelector('.signposting-rule-people');
    const signen = document.querySelector('.signposting-rule-endeavor');
    
    const detailhotel = document.querySelector('.detail-hotel');
    const detailpeople = document.querySelector('.detail-people');
    const detailen = document.querySelector('.detail-endeavor');
    
    const headinghotel = document.querySelector('.heading-hotel');
    const headingpeople = document.querySelector('.heading-people');
    const headingen = document.querySelector('.heading-endeavor');
    const contenthotel = document.querySelector('.content-hotel');
    const contentpeople = document.querySelector('.content-people');
    const contenten = document.querySelector('.content-endeavor');
    
    if(isInViewport(signhotel)){
        signhotel.classList.add('visible');
        detailhotel.classList.add('visible');
        headinghotel.classList.add('visible');
        contenthotel.classList.add('visible');
    }
    
    if(isInViewport(signpeople)){
        signpeople.classList.add('visible');
        detailpeople.classList.add('visible');
        headingpeople.classList.add('visible');
        contentpeople.classList.add('visible');
    }
    
    if(isInViewport(signen)){
        signen.classList.add('visible');
        detailen.classList.add('visible');
        headingen.classList.add('visible');
        contenten.classList.add('visible');
    }
}

window.addEventListener('scroll', handleScroll);

