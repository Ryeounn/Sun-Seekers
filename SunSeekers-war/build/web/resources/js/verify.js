var fullUrl = window.location.href;
if (fullUrl === 'http://localhost:8081/SunSeekers-war/faces/client/forget.xhtml') {
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

function moveToNextInput(currentInput, nextInputIndex) {
    var currentInput = document.querySelector('.form-control.txt--code:nth-child(' + nextInputIndex + '):not([readonly]):not([disabled])');

    if (currentInput) {
        var currentValue = currentInput.value;

        if (currentValue && nextInputIndex < 6) {
            var nextInput = document.querySelector('.form-control.txt--code:nth-child(' + (nextInputIndex + 1) + '):not([readonly]):not([disabled])');

            if (nextInput) {
                nextInput.focus();
            }
        }
    }

}

function pasteHandler(event, currentInput) {
    event.preventDefault();

    // Lấy nội dung được paste
    var pastedText = (event.clipboardData || window.clipboardData).getData('text');

    // Tách và gán từng ký tự vào từng ô
    for (var i = 0; i < pastedText.length && i < 6; i++) {
        var currentChar = pastedText.charAt(i);

        // Gán giá trị vào ô nhập liệu hiện tại
        currentInput.value = currentChar;

        // Chuyển đến ô nhập liệu tiếp theo
        var nextInput = currentInput.nextElementSibling;
        if (nextInput) {
            currentInput = nextInput;
        }
    }
}

