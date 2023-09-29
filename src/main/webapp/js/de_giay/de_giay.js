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
                    if (data == 0) {
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


// Delete
function Delete(id) {
    if (confirm("Bạn có chắc chắn muốn xóa không ?")) {
        $.ajax({
            type: "POST",
            url: 'Product/Delete/' + id,
            success: function (data) {
                if (data.success) {
                    dataTable.ajax.reload();

                    $.notify(data.message, {
                        globalPosition: "top center",
                        className: "success"
                    });
                }
            }
        });
    }
}

