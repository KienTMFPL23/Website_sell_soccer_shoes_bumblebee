var PopupForm, dataTable;

$(document).ready(function () {
    dataTable = $("#table").DataTable({
        ajax: {
            "url": "/de-giay/hien-thi/list",
            "type": "GET",
            "datatype": "json",
            "dataSrc": ""
        },
        columns: [
            {"data": "ma"},
            {"data": "loaiDe"},
            {"data": "moTa"},
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
                    return "<a  onclick=PopupFormEdit('/de-giay/view-update/" + data + "')> <img src='../../img/Edit_Notepad_Icon.svg' style='width: 30px; height: 30'/> </a>";
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


// Load Form Edit
function PopupFormEdit(url) {
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

// Submit Form
function SubmitForm(form) {
    $.validator.unobtrusive.parse(form);
    if ($(from).valid()) {

        $.ajax({
            type: "POST",
            url: form.action,
            data: $(form).serialize(),
            success: function (data) {
                if (data.success) {
                    Popup.dialog('close');
                    dataTable.ajax.reload();

                    $.notify(data.message, {
                        globalPosition: "top center",
                        className: "success"
                    });
                }
            }
        });
    }
    return false;
}

function Validate() {
    // Validation
    $("#formAddUpdate").validate({
        // Rules for form validation
        rules: {
            ma: {
                required: true,
                minlength: 4,
                maxlength: 100
            },
            loaiDe: {
                required: true
            },
            moTa: {
                required: true
            },
            trangThai: {
                required: true
            }
        },

        // Messages for form validation
        messages: {
            ma: {
                required: 'Mã không được để trống',
                minlength: 'Mã phải có ít nhất 4 kí tự',
                maxlength: 'Mã chỉ tối đa 100 kí tự'
            },
            loaiDe: {
                required: 'Loại đế không được để trống'
            },
            moTa: {
                required: 'Mô tả không được để trống'
            },
            trangThai: {
                required: 'Trạng thái không được để trống'
            }
        },

        errorPlacement: function (error, element) {
            error.insertAfter(element.parent());
        }
    });
}


