function filterTable() {
    var mauSac = document.getElementById("selectMS").value;
    var chatLieu = document.getElementById("selectCL").value;
    var deGiay = document.getElementById("selectDG").value;
    var kichCo = document.getElementById("selectKC").value;
    var loaiGiay = document.getElementById("selectLG").value;
    var table = document.getElementById("myTable");
    var rows = table.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var mauSacCell = rows[i].getElementsByTagName("td")[0];
        var chatLieuCell = rows[i].getElementsByTagName("td")[1];
        var deGiayCell = rows[i].getElementsByTagName("td")[2];
        var kichCoCell = rows[i].getElementsByTagName("td")[3];
        var loaiGiayCell = rows[i].getElementsByTagName("td")[4];

        if (mauSacCell && chatLieuCell && deGiayCell && kichCoCell && loaiGiayCell) {
            var mauSacText =    mauSacCell.textContent || mauSacCell.innerText;
            var chaLieuText = chatLieuCell.textContent || chatLieuCell.innerText;
            var deGiayText = deGiayCell.textContent || deGiayCell.innerText;
            var kichCoText = kichCoCell.textContent || kichCoCell.innerText;
            var loaiGiayText = loaiGiayCell.textContent || loaiGiayCell.innerText;

            var mauSacMatch = mauSac === "" || mauSacText === mauSac;
            var chatLieuMatch = chatLieu === "" || chaLieuText === chatLieu;
            var deGiayMatch = deGiay === "" || deGiayText === deGiay;
            var kichCoMatch = kichCo === "" || kichCoText === kichCo;
            var loaiGiayMatch = loaiGiay === "" || loaiGiayText === loaiGiay;

            if (mauSacMatch && chatLieuMatch && deGiayMatch && kichCoMatch && loaiGiayMatch) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
}
document.getElementById("selectMS").addEventListener("change", filterTable);
document.getElementById("selectCL").addEventListener("change", filterTable);
document.getElementById("selectDG").addEventListener("change", filterTable);
document.getElementById("selectKC").addEventListener("change", filterTable);
document.getElementById("selectLG").addEventListener("change", filterTable);