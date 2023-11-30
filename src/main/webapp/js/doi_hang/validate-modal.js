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
    }if( textLydo.empty()){
        var erorText = document.getElementById('erorText');
        erorText.innerText = 'Vui lòng nhập lý do đổi trả';
        return false;
    }
    else {
        return true;
    }
}

// submitButton.addEventListener('click', function (event) {
//     var numberOfCheckedCheckboxes = 0;
//     checkboxes.forEach(function (checkbox) {
//         if (checkbox.checked) {
//             numberOfCheckedCheckboxes++;
//         }
//     });
//     if (numberOfCheckedCheckboxes === 0) {

//         return false;
//     } else {
//         return true;
//     }

//         event.preventDefault();
//         return;
//     }
//     Swal.fire({
//         position: "center",
//         icon: "success",
//         title: "Your data has been saved",
//         showConfirmButton: false,
//         timer: 1500
//     });
// });