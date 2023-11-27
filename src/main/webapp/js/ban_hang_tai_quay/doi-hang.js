
let scannerHoaDon;
const videoHoaDon = document.getElementById('scanHoaDon');
function startScan() {
    scannerHoaDon = new Instascan.Scanner({ video: videoHoaDon });
    Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
            scannerHoaDon.start(cameras[0]);
        } else {
            console.error('No cameras found.');
        }
    }).catch(function (err) {
        console.error('Error accessing cameras:', err);
    });
    scannerHoaDon.addListener("scan",function (qrCode) {
        window.location.href = `/bumblebee/don-hang/searchByQr/${qrCode}`;
    })
}
function stopScan() {
    if (scannerHoaDon) {
        scannerHoaDon.stop();
        videoHoaDon.srcObject = null;
    }
}