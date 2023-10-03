var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#tableLoaiGiay").DataTable({
        ajax: {
            "url": "/loai-giay/hien-thi/list",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "ma"},
            {"data": "tentheloai"},
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
                    return "<a  onclick=PopupFormEdit('/loai-giay/edit/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
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


// Load Form Create
function PopupForm(url) {
    var formDiv = $('<div/>');
    $.get(url).done(function (response) {
        formDiv.html(response);

        Popup = formDiv.dialog({
            autoOpen: true,
            resizable: false,
            height: 700,
            width: 750,
            close: function () {
                Popup.dialog('destroy').remove();
            }
        });
    });
}
//
//
// // Load Form Edit
// // function PopupFormEdit(url) {
// //     var formDiv = $('<div/>');
// //     $.get(url).done(function (response) {
// //         formDiv.html(response);
// //
// //         Popup = formDiv.dialog({
// //             autoOpen: true,
// //             resizable: false,
// //             height: 700,
// //             width: 750,
// //             close: function () {
// //                 Popup.dialog('destroy').remove();
// //             }
// //         });
// //     });
// // }
//
// // Submit Form
// function SubmitForm(form) {
//     $.validator.unobtrusive.parse(form);
//     if ($(from).valid()) {
//
//         $.ajax({
//             type: "POST",
//             url: form.action,
//             data: $(form).serialize(),
//             success: function (data) {
//                 if (data.success) {
//                     Popup.dialog('close');
//                     dataTable.ajax.reload();
//
//                     $.notify(data.message, {
//                         globalPosition: "top center",
//                         className: "success"
//                     });
//                 }
//             }
//         });
//     }
//     return false;
// }




