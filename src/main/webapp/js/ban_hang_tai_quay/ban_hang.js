// let scanner = new Instascan.Scanner({video: document.getElementById('video')});
const startButton = document.getElementById('startButton');
const stopButton = document.getElementById('stopButton');
let scanner;
startButton.addEventListener('click',()=>{
    scanner = new Instascan.Scanner({video: document.getElementById('video')})
    Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
            scanner.start(cameras[0]);
            startButton.style.display = 'none';
            stopButton.style.display = 'block';
        } else {
            alert('Cameras found');
        }
    }).catch(function (e) {
        console.error(e);
    });
});
stopButton.addEventListener('click',() =>{
    if (scanner){
        scanner.stop();
        startButton.style.display = 'block';
        stopButton.style.display = 'none';
    }
});

scanner.addListener("scan", function (qrCode,event) {
    event.preventDefault();
    // Chuyển người dùng đến trang controller khi quét thành công
    window.location.href =  `/bumblebee/ban-hang-tai-quay/add-gio-hang/${qrCode}`;
});



