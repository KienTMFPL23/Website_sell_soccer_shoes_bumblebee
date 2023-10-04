var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableMauSac").DataTable({
        ajax: {
            "url": "/mau-sac/hien-thi/list",
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
                        return '<td>Không hoạt động</td>'
                    } else {
                        return '<td>Hoạt động</td>'
                    }
                }
            },
            {
                "data": "id",
                "render": function (data) {
                    return "<a href= '/mau-sac/update/" + data + "'> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
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