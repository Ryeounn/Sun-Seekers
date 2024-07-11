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
    const signasian = document.querySelector('.signposting-rule-asian');
    const signeurope = document.querySelector('.signposting-rule-europe');
    const signafrica = document.querySelector('.signposting-rule-africa');
    const signaustralia = document.querySelector('.signposting-rule-australia');
    const signamerican = document.querySelector('.signposting-rule-american');
    
    const detailasian = document.querySelector('.detail-asian');
    const detaileurope = document.querySelector('.detail-europe');
    const detailafrica = document.querySelector('.detail-africa');
    const detailaustralia = document.querySelector('.detail-australia');
    const detailamerican = document.querySelector('.detail-american');
    
    const headingasian = document.querySelector('.heading-asian');
    const headingeurope = document.querySelector('.heading-europe');
    const headingafrica = document.querySelector('.heading-africa');
    const headingaustralia = document.querySelector('.heading-australia');
    const headingamerican = document.querySelector('.heading-american');
    
    const contentasia = document.querySelector('.content-asian');
    const contentsubasia = document.querySelector('.content-sub-asian');
    const contenteurope = document.querySelector('.content-europe');
    const contentafrica = document.querySelector('.content-africa');
    const contentaustralia = document.querySelector('.content-australia');
    const contentamerican = document.querySelector('.content-american');
    
    if(isInViewport(signasian)){
        signasian.classList.add('visible');
        detailasian.classList.add('visible');
        headingasian.classList.add('visible');
        contentasia.classList.add('visible');
        contentsubasia.classList.add('visible');
    }
    
    if(isInViewport(signeurope)){
        signeurope.classList.add('visible');
        detaileurope.classList.add('visible');
        headingeurope.classList.add('visible');
        contenteurope.classList.add('visible');
    }
    
    if(isInViewport(signafrica)){
        signafrica.classList.add('visible');
        detailafrica.classList.add('visible');
        headingafrica.classList.add('visible');
        contentafrica.classList.add('visible');
    }
    
    if(isInViewport(signaustralia)){
        signaustralia.classList.add('visible');
        detailaustralia.classList.add('visible');
        headingaustralia.classList.add('visible');
        contentaustralia.classList.add('visible');
    }
    
    if(isInViewport(signamerican)){
        signamerican.classList.add('visible');
        detailamerican.classList.add('visible');
        headingamerican.classList.add('visible');
        contentamerican.classList.add('visible');
    }
}

window.addEventListener('scroll', handleScroll);