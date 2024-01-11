let scanner;
const videoHoaDon = document.getElementById('video');
function startScan() {
    scanner = new Instascan.Scanner({video: videoHoaDon});
    Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
            scanner.start(cameras[0]);
        } else {
            console.error('No cameras found.');
        }
    }).catch(function (err) {
        console.error('Error accessing cameras:', err);
    });
    scanner.addListener("scan", function (qrCode, event) {
        window.location.href = `/bumblebee/ban-hang-tai-quay/add-gio-hang/${qrCode}`;

    });
}
function stopScan() {
    if (scanner) {
        scanner.stop();
        videoHoaDon.srcObject = null;
    }
}

