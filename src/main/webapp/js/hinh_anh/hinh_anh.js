var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableHinhAnh").DataTable({
        ajax: {
            "url": "/hinh-anh/hien-thi/list",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "ctsp.id"},
            {
                "data": "tenanh",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='150' height='150' style='object-fit: cover' />"
                }
            },
            {
                "data": "duongdan1",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='150' height='150' style='object-fit: cover' />"
                }
            },
            {
                "data": "duongdan2",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='150' height='150' style='object-fit: cover' />"
                }
            },
            {
                "data": "duongdan3",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='150' height='150' style='object-fit: cover' />"
                }
            },
            {
                "data": "duongdan4",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='150' height='150' style='object-fit: cover' />"
                }
            },
            {
                "data": "duongdan5",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='150' height='150' style='object-fit: cover' />"
                }
            },
            {
                "data": "id",
                "render": function (data) {
                    return "<a href='/hinh-anh/view-update/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
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

