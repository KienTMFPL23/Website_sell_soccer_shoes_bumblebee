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
               window.location.href = `/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/${idHoaDon}`
            },
            error: function () {
                $("#ten").html("Error send data");
            }
        });
    });
});
