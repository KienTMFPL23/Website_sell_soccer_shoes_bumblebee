var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableHinhAnh").DataTable({
        ajax: {
            "url": "/bumblebee/ban-hang-tai-quay/view-modal",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "ctsp.id"},
            {
                "data": "tenanh",
                "render": function (data) {
                    return "<img src='../../uploads/" + data + "' width='130' height='130' style='object-fit: cover' />"
                }
            },
            {
                "data": "id",
                "render": function (data) {;
                    return "<a href='/bumblebee/ban-hang-tai-quay/add-gio-hang/ " + data +"' class='btn btn-primary'>add</a>"
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

