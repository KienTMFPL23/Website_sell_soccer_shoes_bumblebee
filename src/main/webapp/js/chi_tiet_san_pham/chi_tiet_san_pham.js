
    $(document).ready(function () {
    $('#searchName').select2({
        width: 200,
        placeholder: "Search Loai Giay ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-loai-giay',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.tentheloai,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});

 $(document).ready(function () {
    $('#searchName1').select2({
        width: 200,
        placeholder: "Search Size ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-kich-co',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.size,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {
    $('#searchName2').select2({
        width: 200,
        placeholder: "Search Mau Sac ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-mau-sac',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {
    $('#searchName3').select2({
        width: 200,
        placeholder: "Search De Giay ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-de-giay',
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


$(document).ready(function () {
    $('#searchName4').select2({
        width: 200,
        placeholder: "Search Chat Lieu ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-chat-lieu',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
