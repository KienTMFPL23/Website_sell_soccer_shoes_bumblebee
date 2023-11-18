
function thayDoiSoLuong() {
    var sl = $("#sl").val();
    if (Number(sl) < 1) {
        document.getElementById("sl").value = Number(1);
    }
    if (Number(sl) >= response) {
        document.getElementById("sl").value = Number(response);
    }
}

document.getElementById("#sl").addEventListener("change", function () {
    var sl = $("#sl").val();
    if (Number(sl) < 0 || Number(sl) === 0) {
        document.getElementById("sl").value = Number(1);
    }
    if (Number(sl) >= response) {
        document.getElementById("sl").value = Number(response);
    }
})

function closeErrorModal() {
    var modal = document.getElementById("errorModal");
    var modal1 = document.getElementById("errorKCModal");
    modal1.classList.remove('show');
    modal1.classList.add('hide');
    setTimeout(function() {
        modal1.style.display = 'none';
        modal1.classList.remove('hide');
    }, 300);
    modal.style.display = "none";
}

