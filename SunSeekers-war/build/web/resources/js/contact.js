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
    const signcontact = document.querySelector('.signposting-rule-contact');
    
    const detailcontact = document.querySelector('.detail-contact');
    
    const headingcontact = document.querySelector('.heading-large-contact');
    const contacttel = document.querySelector('.contact-tel');
    const contactmail = document.querySelector('.contact-mail');
    const contactaddress = document.querySelector('.contact-address');
    
    if(isInViewport(signcontact)){
        signcontact.classList.add('visible');
        detailcontact.classList.add('visible');
        headingcontact.classList.add('visible');
        contacttel.classList.add('visible');
        contactmail.classList.add('visible');
        contactaddress.classList.add('visible');
    }
}

window.addEventListener('scroll', handleScroll);


