var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableKichCo").DataTable({
        ajax: {
            "url": "/kich-co/hien-thi/list",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "maKichCo"},
            {"data": "size"},
            {
                "data": "gioiTinh",
                "render": function (data) {
                    if (data == 1) {
                        return '<td>Nam</td>'
                    } else {
                        return '<td>Nữ</td>'
                    }
                }
            },
            {
                "data": "trangThai",
                "render": function (data) {
                    if (data == 1) {
                        return '<td>Hoạt động</td>'
                    } else {
                        return '<td>Không hoạt động</td>'
                    }
                }
            },
            {
                "data": "id",
                "render": function (data) {
                    return "<a  href='/kich-co/view-update/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
                },
                "orderable": false,
            }
        ],
        language: {
            search: "",
            searchPlaceholder: "Tìm kiếm ......",
        }
    });
});
