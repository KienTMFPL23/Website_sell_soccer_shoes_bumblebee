$(document).ready(function () {
    $('#searchName').select2({
        width: 400,
        placeholder: "Search Name ....",
        ajax: {
            type: 'GET',
            url: '/de-giay/tim-kiem',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.loaiDe,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});





