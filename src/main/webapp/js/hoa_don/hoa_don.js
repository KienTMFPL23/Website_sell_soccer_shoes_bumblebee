$(document).ready(function () {
    $('#searchNameStatus').select2({
        width: 150,
        placeholder: "Search Status ....",
        ajax: {
            type: 'GET',
            url: '/hoa-don/search-by-status',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.status,
                            id: item.trangThai
                        }
                    })
                };
            }
        }
    });
});