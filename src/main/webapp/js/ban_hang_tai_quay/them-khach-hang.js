$(document).ready(function () {
    $("#sendKhachHang").submit(function () {
        var requestData = {
            "ten": document.getElementById('customer').value,
            "soDienThoai": document.getElementById('getSDT').value
        };
        $.ajax({
            type: "POST",
            url: "/bumblebee/ban-hang-tai-quay/them-khach-hang",
            data: requestData,
            success: function (event) {
                event.preventDefault();
                console.log("thanh cong");
            },
            error: function () {
                $("#ten").html("Error send data");
            }
        });
    });
});