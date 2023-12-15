// let scanner = new Instascan.Scanner({video: document.getElementById('video')});
// Instascan.Camera.getCameras().then(function (cameras) {
//     if (cameras.length > 0) {
//         scanner.start(cameras[0]);
//     } else {
//         alert('Cameras found');
//     }
// }).catch(function (e) {
//     console.error(e);
// });

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
        // event.preventDefault();
        // Chuyển người dùng đến trang controller khi quét thành công
        window.location.href = `/bumblebee/ban-hang-tai-quay/add-gio-hang/${qrCode}`;
        var data = eval('('+'${erorSP}'+')');
        if (data === "Không có sản phẩm nào!!!"){
            event.preventDefault();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Thaast bai",
                showConfirmButton: true,
            });
        }else {
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Thêm thành công",
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout(function () {
                return true;
            }, 1500);
        }
    });
}

function stopScan() {
    if (scanner) {
        scanner.stop();
        videoHoaDon.srcObject = null;
    }
}

