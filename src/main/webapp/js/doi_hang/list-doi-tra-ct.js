function showDoiTraCT(id) {
    $(document).ready(function () {
        loadHoaDonCT();
        loadDoiTraCT()
    });
    function loadHoaDonCT(){
        $.ajax({
            url: `/bumblebee/don-hang/list-hoa-don-ct/${id}`,
            method: 'GET',
            success: function(response) {
                console.log(response)
                tableHoaDonCT(response);
            },
            error: function() {
                console.error('Error fetching data.');
            }
        });
    }
    function loadDoiTraCT(){
        $.ajax({
            url: `/bumblebee/don-hang/list-doi-tra-ct/${id}`,
            method: 'GET',
            success: function(response) {
                console.log(response)
                tableDoiTraCT(response);
            },
            error: function() {
                console.error('Error fetching data.');
            }
        });
    }
    function tableHoaDonCT(data) {
        var bodyHoaDon = $('#tableHoaDonCT');
        bodyHoaDon.empty();

        $.each(data, function(index, item) {
            var row = '<tr>' +
                '<td>' + item.tenSanPham + '</td>' +
                '<td>' + item.soLuong + '</td>' +
                '<td>' + item.donGia + '</td>' +
                '<td>' + item.tenMau + '</td>' +
                '<td>' + item.size + '</td>' +
                '<td>' + '<c:if test="${item.trangThai == 1}"><button class="btn btn-success">Đã mua</button></c:if>' + '</td>' +
                '</td>' +
                '</tr>';
            bodyHoaDon.append(row);
        });

    }
    function tableDoiTraCT(data) {
        var bodyDoiTra = $('#tableDoiTra');
        bodyDoiTra.empty();
        var sumMoney = 0.0;
        $.each(data, function(index, item) {
            sumMoney = item.soLuong * item.donGia;
            var row = '<tr>' +
                '<td>' + item.tenSanPham + '</td>' +
                '<td>' + item.soLuong + '</td>' +
                '<td>' + item.donGia + '</td>' +
                '<td>' + item.tenMau + '</td>' +
                '<td>' + item.size + '</td>' +
                '<td>' + item.lyDoDoiTra + '</td>' +
                '<td>' + '<c:if test="${item.trangThai == 2}"><button class="btn btn-danger">Hàng trả</button></c:if>' + '</td>' +
                '</tr>';
            bodyDoiTra.append(row);
            var row1 = '<tr>'+ sumMoney +'</tr>';
            bodyDoiTra.append(row1);
        });

    }
}

