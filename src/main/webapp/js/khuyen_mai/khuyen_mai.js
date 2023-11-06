var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableKhuyenMai").DataTable({
        ajax: {
            "url": "/bumblebee/khuyen-mai/hien-thi",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "maKhuyenMai"},
            {"data": "tenKhuyenMai"},
            {"data": "giaTri"},
            {"data": "ngayTao"},
            {
                "data": "trangThai",
                "render": function (data) {
                    if (data == 0) {
                        return '<td>Hoạt động</td>'
                    } else {
                        return '<td>Không hoạt động</td>'
                    }
                }
            }
            // ,
            // {
            //     "data": "id",
            //     "render": function (data) {
            //         return "<a href='/de-giay/view-update/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
            //     },
            //     "orderable": false,
            // }
        ],
        language: {
            search: "",
            searchPlaceholder: "Tìm kiếm ......",
        }
    });
});



