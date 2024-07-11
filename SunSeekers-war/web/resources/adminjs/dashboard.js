var colorctx3 = ["red", "blue", "green", "yellow"];
var ctx = document.getElementById('total');
new Chart(ctx, {
    type: 'line',
    
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});



