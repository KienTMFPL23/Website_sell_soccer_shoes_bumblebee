var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableKH").DataTable({
        ajax: {
            "url": "/khach-hang/hien-thi/list",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "ma"},
            {"data": "ten"},
            {"data": "gioiTinh"},
            {"data": "ngaySinh"},
            {"data": "diaChi"},
            {"data": "soDienThoai"},
            {"data": "email"},
            {
                "data": "trangThai",
                "render": function (data) {
                    if (data === 1) {
                        return '<td>Hoạt Động</td>'
                    } else {
                        return '<td>Ngừng Hoạt Động</td>'
                    }
                }
            },
            {
                "data": "id",
                "render": function (data) {
                    return "<a  href=('/khach-hang/edit/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
                },
                "orderable": false,
            }
        ],
        language: {
            search: "",
            searchPlaceholder: "Tìm kiếm ......",
        }
        // serverSide: true,
        // paging: true,
        // processing: true,
        // language: {
        //     processing: "Processing.... please wait"
        // }
    });
});