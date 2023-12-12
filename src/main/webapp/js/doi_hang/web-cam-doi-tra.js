let scanner;
const videoHoaDon = document.getElementById('camHoaDon');

function openWebcam() {
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
        // event.preventDefault();
        // Chuyển người dùng đến trang controller khi quét thành công
        window.location.href = `/bumblebee/don-hang/${qrCode}`;
        var data = eval('('+'${notFound}'+')');
        if (data === 'Khong tim thay'){
            event.preventDefault();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Hóa đơn không đủ điều kiện đổi trả",
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout(function () {
                return true;
            }, 1500);
        }else {
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Thành công",
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout(function () {
                return true;
            }, 1500);
        }
    });
}

function closeWebcam() {
    if (scanner) {
        scanner.stop();
        videoHoaDon.srcObject = null;
    }
}