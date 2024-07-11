var center_content = document.getElementById("content");
const body = document.querySelector("body"),
        sidebar = body.querySelector(".sidebar"),
        toggle = body.querySelector(".toggle"),
        searchBtn = body.querySelector(".search-box"),
        modeSwitch = body.querySelector(".toggle-switch"),
        modeText = body.querySelector(".mode-text");

toggle.addEventListener("click", () => {
    if (sidebar.classList.contains("close")) {
        sidebar.classList.remove("close");
        center_content.classList.add("testmargin");
    } else {
        sidebar.classList.add("close");
        center_content.classList.remove("testmargin");
    }
});

//searchBtn.addEventListener("click", () => {
//    sidebar.classList.remove("close");
//    contenter.classList.remove('axfjiJerf');
//});

//      modeSwitch.addEventListener("click", ()=>{
//         body.classList.toggle("dark"); 
//         
//         if(body.classList.contains("dark")){
//             modeText.innerText = "Light Mode"
//         }else{
//             modeText.innerText = "Dark Mode"
//         }
//      });

//let mode = localStorage.getItem('darkmode');
//if (mode == 'true') {
//    body.classList.add("dark");
//}
//modeSwitch.addEventListener('click', () => {
//    let mode = body.classList.toggle('dark');
//    // save mode
//    localStorage.setItem('darkmode', mode);
//});
//
//document.querySelectorAll('.nav-link > a').forEach(item => {
//    item.addEventListener('click', event => {
//        event.preventDefault(); // Prevent default link behavior
//        const parent = item.parentElement;
//        parent.classList.toggle('active');
//    });
//});


const toggleButton = document.getElementById('toggleButton');
const modal = document.getElementById('modal-dash');
const bb8Toggle = document.getElementById('bb8Toggle');

const colors = document.querySelectorAll('.input-custom-color');
const toggleDarkMode = document.getElementById('toggleButton');
        let currentColorClass = localStorage.getItem('sidebarColor') || '';
        
        // Function to update the sidebar color based on dark mode state
        function updateSidebarColor() {
            if (toggleDarkMode.checked) {
                sidebar.classList.add('dark-mode');
            } else {
                sidebar.classList.remove('dark-mode');
                sidebar.className = 'sidebar';  // Reset all classes
                if (currentColorClass) {
                    sidebar.classList.add(currentColorClass);
                }
            }
        }

        // Load the saved color and dark mode state
        if (currentColorClass) {
            sidebar.classList.add(currentColorClass);
        }
        const darkModeState = localStorage.getItem('darkMode');
        if (darkModeState === 'enabled') {
            toggleDarkMode.checked = true;
            sidebar.classList.add('dark-mode');
        }

        // Add event listener for color change
        colors.forEach(color => {
            color.addEventListener('click', () => {
                // Get the color class of the clicked element
                const newColorClass = [...color.classList].find(cls => cls !== 'input-custom-color');

                // Remove the current color class if it exists
                if (currentColorClass) {
                    sidebar.classList.remove(currentColorClass);
                }

                // Add the new color class to the sidebar
                if (newColorClass) {
                    sidebar.classList.add(newColorClass);
                    currentColorClass = newColorClass;
                    localStorage.setItem('sidebarColor', currentColorClass);
                }

                // Update the sidebar color based on the dark mode state
                updateSidebarColor();
                
                if (toggleDarkMode.checked) {
            sidebar.classList.add('dark');
        } else {
            sidebar.classList.remove('dark');
        }
            });
        });

        // Add event listener for dark mode toggle
        toggleDarkMode.addEventListener('change', () => {
            if (toggleDarkMode.checked) {
                localStorage.setItem('darkMode', 'enabled');
            } else {
                localStorage.setItem('darkMode', 'disabled');
            }
            updateSidebarColor();
        });
        
        toggleButton.addEventListener('click', function () {
    if (modal.classList.contains('active')) {
        closeModal();
    } else {
        openModal();
    }
});

modal.addEventListener('click', function (event) {
    if (event.target === modal) {
        closeModal();
    }
});

function openModal() {
    modal.classList.add('active');
}

function closeModal() {
    modal.classList.remove('active');
}
            
            let angle = 0;
        const speed = 3; // Điều chỉnh tốc độ quay, số càng nhỏ thì quay càng chậm

        function rotateCog() {
            angle = (angle + speed) % 360;
            toggleButton.style.transform = `rotate(${angle}deg)`;
            requestAnimationFrame(rotateCog);
        }

        rotateCog();

const bb8Container = document.getElementById('bb8Container');
const toggleSwitch = document.getElementById('toggle');

// Lấy trạng thái của dark mode từ localStorage
const isDarkMode = localStorage.getItem('darkMode') === 'true';
// Lấy vị trí của bb8-toggle__container từ localStorage
const bb8ContainerPosition = localStorage.getItem('bb8ContainerPosition');

// Nếu có vị trí của bb8-toggle__container, cập nhật nó
if (bb8ContainerPosition) {
    bb8Container.style.left = bb8ContainerPosition;
}

// Nếu dark mode được kích hoạt từ trước, đặt toggle switch vào trạng thái checked
if (isDarkMode) {
    document.body.classList.add('dark');
    sidebar.classList.add('dark');
    toggleSwitch.checked = true;
}

// Lắng nghe sự kiện thay đổi trạng thái của toggle switch
toggleSwitch.addEventListener('change', function () {
    // Cập nhật trạng thái của dark mode trong localStorage
    localStorage.setItem('darkMode', this.checked);
    // Nếu toggle được check, thêm class 'dark' vào body, ngược lại, loại bỏ nó
    document.body.classList.toggle('dark', this.checked);

    // Nếu toggle được check, lưu vị trí của bb8-toggle__container vào localStorage
    if (this.checked) {
        localStorage.setItem('bb8ContainerPosition', bb8Container.style.left);
    }
});
