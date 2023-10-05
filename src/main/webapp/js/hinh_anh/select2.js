$(document).ready(function () {
    $('#selectCTSP').select2({
        width: 460,
        placeholder: " ",
        ajax: {
            type: 'GET',
            url: '/ctsp/search',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.id,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});





