var kichCo = document.getElementById("kichCoList").value;
if (kichCo == 1) {
    $(".btn-themgh").prop("disabled", true);
    $(".btn-mua").prop("disabled", true);
}

selectSize();

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
    modal.style.display = "none";
}

