const modal = document.getElementById('modalTraHang');
const checkbox = document.getElementById('checkBoxSP');
const submitButton = document.getElementById('xacNhanTra');
const lyDo = document.getElementById('lyDoTra');
var checkboxes = document.querySelectorAll('.checkCart');

function taoDoiTra() {
    var numberOfCheckedCheckboxes = 0;
    var textLydo = lyDo.value;
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            numberOfCheckedCheckboxes++;
        }
    });
    if (numberOfCheckedCheckboxes === 0) {
        var logEror = document.getElementById('logError');
        logEror.innerText = 'Vui lòng chọn sản phẩm cần trả';
        return false;
    }if( textLydo === ''){
        var erorText = document.getElementById('erorText');
        erorText.innerText = 'Vui lòng nhập lý do đổi trả';
        return false;
    }
    else {
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Your data has been saved",
            showConfirmButton: false,
            timer: 1500
        });
        return true;
    }
}
function validateSoLuong(max) {
    var soLuong = document.getElementById('soLuongTra');
    var errorMsg = document.getElementById("errorMsg");
    if (soLuong.value > max){
       soLuong.value = max;
    }else  if(soLuong.value <= 0){
        soLuong.value = 1;
    }else {
        errorMsg.style.display = "none";
    }
}