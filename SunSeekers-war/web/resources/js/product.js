window.onload = function () {
    const tourBoxes = document.getElementsByClassName('kdoasko-box');

    Array.from(tourBoxes).forEach(tourBox => {
        let currentIndex = 0;
        const images = tourBox.getElementsByClassName('jdopawJwe-inner-img');
        images[currentIndex].style.display = 'block';

        setInterval(() => {
            images[currentIndex].style.display = 'none';
            currentIndex = (currentIndex + 1) % images.length;
            images[currentIndex].style.display = 'block';
        }, 3000);
    });
};


function toggleDeleteButton() {
    var checkboxes = document.querySelectorAll('.jdopawJwe-checkbox');
    var deleteButton = document.getElementById('productForm:deleteBtn');
    var isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
    if (isChecked) {
        deleteButton.classList.remove('disabled');
    } else {
        deleteButton.classList.add('disabled');
    }
}

//function openModalAS() {
//    var modelProduct = document.getElementById('createModalAs');
//    modelProduct.classList.add('show');
//}
//
//function closeModalAS() {
//    var modelProduct = document.getElementById('createModalAs');
//    modelProduct.classList.remove('show');
//}
//
//// Đóng modal khi người dùng bấm vào nút "x"
//document.addEventListener('DOMContentLoaded', (event) => {
//    var closeBtn = document.querySelector('.closee');
//    closeBtn.onclick = function () {
//        closeModalAS();
//    };
//
//    window.onclick = function (event) {
//        var modalAs = document.getElementById('createModalAs');
//        if (event.target === modalAs) {
//            closeModalAS();
//        }
//    };
//});