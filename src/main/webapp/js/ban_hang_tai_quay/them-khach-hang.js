$(document).ready(function () {
    $("#sendKhachHang").submit(function (event) {
        event.preventDefault();
        var requestData = {
            "ten": document.getElementById('customer').value,
            "soDienThoai": document.getElementById('getSDT').value
        };
        $.ajax({
            type: "POST",
            url: "/bumblebee/ban-hang-tai-quay/them-khach-hang",
            data: requestData,
            success: function () {

                console.log("thanh cong");
            },
            error: function () {
                $("#ten").html("Error send data");
            }
        });
    });
});