window.onload = function () {
    const tourBoxes = document.getElementsByClassName('tour-box-dt');

    Array.from(tourBoxes).forEach(tourBox => {
        let currentIndex = 0;
        const images = tourBox.getElementsByClassName('graphic-tour');
        images[currentIndex].style.display = 'block';

        setInterval(() => {
            images[currentIndex].style.display = 'none';
            currentIndex = (currentIndex + 1) % images.length;
            images[currentIndex].style.display = 'block';
        }, 3000);
    });
};

document.addEventListener('DOMContentLoaded', function () {
    attachToggleEvents();
});

function attachToggleEvents() {
    var toggleLinks = document.querySelectorAll('.toggleLink');
    var toggleLinks2 = document.querySelectorAll('.toggleLink2');
    var toggleLinks3 = document.querySelectorAll('.toggleLink3'); // Thêm dòng này

    toggleLinks.forEach(function (toggleLink) {
        toggleLink.onclick = function (event) {
            toggleExtraContent(toggleLink, 1);
        };
    });

    toggleLinks2.forEach(function (toggleLink2) {
        toggleLink2.onclick = function (event) {
            toggleExtraContent(toggleLink2, 2);
        };
    });

    toggleLinks3.forEach(function (toggleLink3) { // Thêm đoạn này
        toggleLink3.onclick = function (event) {
            toggleExtraContent(toggleLink3, 3);
        };
    });
}

function toggleExtraContent(link, type) {
    event.preventDefault();
    var contentTourbox = link.closest('.tour-box-dt');
    var content, expandedClass, otherTypes;

    if (type === 1) {
        content = contentTourbox.querySelector('.extra-content');
        expandedClass = 'expanded';
        otherTypes = ['expanded2', 'expanded3'];
    } else if (type === 2) {
        content = contentTourbox.querySelector('.extra-content2');
        expandedClass = 'expanded2';
        otherTypes = ['expanded', 'expanded3'];
    } else {
        content = contentTourbox.querySelector('.extra-content3'); // Thêm đoạn này
        expandedClass = 'expanded3';
        otherTypes = ['expanded', 'expanded2'];
    }

    if (contentTourbox.classList.contains(expandedClass)) {
        contentTourbox.style.height = '160px';
        contentTourbox.classList.remove(expandedClass);
        link.classList.remove('link-active');
        content.classList.remove('ct-visibility');
    } else {
        contentTourbox.style.height = type === 1 ? '50px' : type === 2 ? '600px' : '770px'; // Điều chỉnh chiều cao cho loại mới
        contentTourbox.classList.add(expandedClass);
        link.classList.add('link-active');
        content.classList.add('ct-visibility');
    }

    otherTypes.forEach(function (otherType) {
        contentTourbox.classList.remove(otherType);
    });

    var otherLinks, otherContents;
    if (type === 1) {
        otherLinks = contentTourbox.querySelectorAll('.toggleLink2, .toggleLink3');
        otherContents = contentTourbox.querySelectorAll('.extra-content2, .extra-content3');
    } else if (type === 2) {
        otherLinks = contentTourbox.querySelectorAll('.toggleLink, .toggleLink3');
        otherContents = contentTourbox.querySelectorAll('.extra-content, .extra-content3');
    } else {
        otherLinks = contentTourbox.querySelectorAll('.toggleLink, .toggleLink2');
        otherContents = contentTourbox.querySelectorAll('.extra-content, .extra-content2');
    }

    otherLinks.forEach(function (otherLink) {
        otherLink.classList.remove('link-active');
    });

    otherContents.forEach(function (otherContent) {
        otherContent.classList.remove('ct-visibility');
    });

    adjustHeight(contentTourbox);
}

function adjustHeight(contentTourbox) {
    var expanded = contentTourbox.classList.contains('expanded');
    var expanded2 = contentTourbox.classList.contains('expanded2');
    var expanded3 = contentTourbox.classList.contains('expanded3');

    if (expanded && expanded2) {
        contentTourbox.style.height = '600px';
    } else if (expanded) {
        contentTourbox.style.height = '450px';
    } else if (expanded2) {
        contentTourbox.style.height = '450px';
    } else if (expanded3) {
        contentTourbox.style.height = '450px';
    } else {
        contentTourbox.style.height = '160px';
    }
};

function filter(element) {
    event.preventDefault();
    var containcheckbox = element.nextElementSibling;
    if (containcheckbox.style.height) {
        containcheckbox.style.height = null;
    } else {
        containcheckbox.style.height = "fit-content";
    }
}

document.addEventListener("DOMContentLoaded", function(event) {
    // Lấy tất cả các phần tử có class tour-choose
    const tourLinks = document.querySelectorAll('.tour-choose');

    // Thêm sự kiện click cho từng phần tử
    tourLinks.forEach(link => {
        link.addEventListener('click', function() {
            // Xóa class add-active khỏi tất cả các phần tử
            tourLinks.forEach(link => link.classList.remove('add-active'));

            // Thêm class add-active vào phần tử được click
            this.classList.add('add-active');
        });
    });
});



