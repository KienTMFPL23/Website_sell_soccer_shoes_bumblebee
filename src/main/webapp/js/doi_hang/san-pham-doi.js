function showDataSP(id) {
    $(document).ready(function () {
        loadData();
    });
    function loadData(){
        $.ajax({
            url: `/bumblebee/don-hang/list-sp/${id}`,
            method: 'GET',
            success: function(response) {
                console.log('success')
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
                '<td>' + '<a href="" class="btn btn-primary">Chọn</a>' + '</td>' +
                '</tr>';
            tableBody.append(row);
        });
    }
}
// $('#openModalSanPham').on('click', function () {
//     // Gửi yêu cầu AJAX khi click mở modal
//     $.ajax({
//         type: 'GET',
//         url: `/bumblebee/don-hang/list-sp/${id}`,
//         success: function (data) {
//             var tableBody = document.getElementById('tableSanPham');
//             data.forEach(function (item) {
//                 var row = tableBody.insertRow();
//                 var cell1 = row.insertCell(0);
//                 var cell2 = row.insertCell(1);
//                 var cell3 = row.insertCell(2);
//
//                 cell1.textContent = item.sanPham.tenSanPham;
//                 cell2.textContent = item.soLuong;
//                 cell3.textContent = item.mauSac.ten;
//             });
//             console.log(data)
//         },
//         error: function () {
//             // Xử lý lỗi nếu có
//             $('#modalContent').html('Error loading data');
//         }
//     });
// });

