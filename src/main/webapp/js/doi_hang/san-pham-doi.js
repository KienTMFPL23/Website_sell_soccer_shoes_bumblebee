function showDataSP(id) {
    $(document).ready(function () {
        loadData();
    });
    function loadData(){
        $.ajax({
            url: `/bumblebee/don-hang/list-sp/${id}`,
            method: 'GET',
            success: function(response) {
                console.log(response)
                populateTable(response);
            },
            error: function() {
                console.error('Error fetching data.');
            }
        });
    }
    function populateTable(data) {
        var tableBody = $('#tableSanPham');
        tableBody.empty();
        $.each(data, function(index, item) {
            var row = '<tr>' +
                '<td>' + item.tenSanPham + '</td>' +
                '<td>' + item.tenMau + '</td>' +
                '<td>' + item.size + '</td>' +
                '<td>' + item.soLuong + '</td>' +
                '<td>' + item.giaBan + '</td>' +
                '<td>' + "<a href='/bumblebee/don-hang/create-doi-tra/" + item.id + "') class='btn btn-primary'>Chọn</a>" + '</td>' +
                '</tr>';
            tableBody.append(row);
        });
    }
}

function showDataSPKM(id) {
    $(document).ready(function () {
        loadDataSPKM();
    });
    function loadDataSPKM(){
        $.ajax({
            url: `/bumblebee/don-hang/list-sp-km/${id}`,
            method: 'GET',
            success: function(response) {
                tableSanPhamKM(response);
            },
            error: function() {
                console.error('Error fetching data.');
            }
        });
    }
    function tableSanPhamKM(data) {
        var tbBody = $('#tableSanPham');
        tbBody.empty();
        $.each(data, function(index, item) {
            var row = '<tr>' +
                '<td>' + item.tenSanPham + '</td>' +
                '<td>' + item.tenMau + '</td>' +
                '<td>' + item.size + '</td>' +
                '<td>' + item.soLuong + '</td>' +
                '<td>' + item.giaBan + '</td>' +
                '<td>' + "<a href='/bumblebee/don-hang/create-doi-tra/" + item.id + "') class='btn btn-primary'>Chọn</a>" + '</td>' +
                '</tr>';
            tbBody.append(row);
        });
    }
}