const modal = document.getElementById('modalTraHang');
const checkbox = document.getElementById('checkBoxSP');
const submitButton = document.getElementById('xacNhanTra');
const lyDo = document.getElementById('lyDoTra');
var checkboxes = document.querySelectorAll('.checkCart');

var checkedAllCheckbox = document.getElementById('checkAll');

checkedAllCheckbox.addEventListener('click', function () {
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = checkedAllCheckbox.checked;
    });
});
function taoDoiTra(idSP) {
    var checkSoLuong = document.querySelectorAll('.soLuongtra');
    checkSoLuong.forEach(function (checkbox) {
        if (checkSoLuong.value === "") {
            alert('Nhap so luong');
            return false;
        }
    });

    var numberOfCheckedCheckboxes = 0;
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            numberOfCheckedCheckboxes++;
        }
    });

    if (numberOfCheckedCheckboxes === 0) {
        var logEror = document.getElementById('logError');
        logEror.innerText = 'Vui lòng chọn sản phẩm cần trả';
        return false;
    }
    if (textLydo === '') {
        var erorText = document.getElementById('erorText');
        erorText.innerText = 'Vui lòng nhập lý do đổi trả';
        return false;
    }
    else {
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Thanh toán thành công",
            showConfirmButton: false,
            timer: 1500
        });
        setTimeout(function () {
            return true;
        },1500);
    }
}
function changeSoLuong(soLuongMax,id) {
    var slTra = document.getElementById('soLuongTra_'+ id);
    if (slTra.value === 0 || slTra.value < 0 || slTra.value === ''){
        slTra.value = 1;
    }else if(slTra.value > soLuongMax ){
        slTra.value = soLuongMax;
    }
}