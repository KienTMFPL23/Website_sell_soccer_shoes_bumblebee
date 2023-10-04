var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableChatLieu").DataTable({
        ajax: {
            "url": "/chat-lieu/hien-thi/list",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "ma"},
            {"data": "ten"},
            {
                "data": "trangThai",
                "render": function (data) {
                    if (data == 1) {
                        return '<td>Còn hàng</td>'
                    } else {
                        return '<td>Hết hàng</td>'
                    }
                }
            },
            {
                "data": "id",
                "render": function (data) {
                    return "<a  href='/chat-lieu/edit/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
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