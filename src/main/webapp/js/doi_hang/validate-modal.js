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

function taoDoiTra() {
    var isChecked = false;
    var isValidQuantity = true;
    var isValidLyDo = true;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            isChecked = true;
            console.log(checkboxes[i].value);
            // Lấy ô input số lượng tương ứng với ô checkbox
            var inputId = checkboxes[i].id.replace('checkBoxSP', 'soLuongTra_' + checkboxes[i].value);
            var lydoId = checkboxes[i].id.replace('checkBoxSP', 'lyDoTra_' + checkboxes[i].value);
            var quantityInput = document.getElementById(inputId);
            var lyDoSelect = document.getElementById(lydoId);
            // Kiểm tra nếu ô input không có giá trị hoặc giá trị không hợp lệ
            if (quantityInput.value.trim() === '' || parseInt(quantityInput.value) < 1) {
                isValidQuantity = false;
            }
            if (lyDoSelect.value === '') {
                isValidLyDo = false;
            }
        }
    }
    if (!isChecked || !isValidQuantity) {
        alert('Vui lòng chọn ít nhất một sản phẩm và nhập số lượng hợp lệ.');
        return false;
    }
    if (!isValidLyDo) {
        alert('Vui lòng chọn lý do đổi trả.');
        return false;
    }
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
    } else {
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Thành công",
            showConfirmButton: false,
            timer: 2000
        });
        setTimeout(function () {
            return true;
        }, 2000);
    }
}


function confirmDoiHang() {
    var result = confirm('Bạn có muốn đổi hàng không ?');
    var lyDo = document.getElementById('lyDo').value;
    var error = document.getElementById('errorLyDo');
    if (result) {
        if (lyDo === "") {
            error.innerText = 'Vui lòng chọn lý do!';
            return false;
        }
        return true;
    } else {
        return false;
    }
}

function chonSoLuong(itemId) {
    const newValue = event.target.value;
    if (newValue == "") {
        document.getElementById("soLuongCTSP_" + itemId).value = 1;
        alert("Số lượng sản phẩm không được để trống");
    }
}
$(document).ready(function () {
    // Add a click event listener to checkboxes with class 'checkCart'
    $('.checkCart').on('click', function () {
        // Initialize total amount
        var totalAmount = 0;
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                // Lấy ô input số lượng tương ứng với ô checkbox
                var inputId = checkboxes[i].id.replace('checkBoxSP', 'soLuongTra_' + checkboxes[i].value);
                var lydoId = checkboxes[i].id.replace('checkBoxSP', 'lyDoTra_' + checkboxes[i].value);
            }
        }

        // Update the content of the 'totalAmount' element
        $('#totalAmount').text('Total Amount: ' + totalAmount.toFixed(2));
    });
});

