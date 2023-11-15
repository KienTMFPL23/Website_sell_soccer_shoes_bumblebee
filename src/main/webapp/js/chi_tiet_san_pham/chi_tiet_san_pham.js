$(document).ready(function () {
  $('#searchName0').select2({
        width: 150,
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
        width: 150,
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
        width: 150,
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
        width: 150,
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
        width: 150,
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
$(document).ready(function () {

  $('#searchName10').select2({
        width: 150,
        placeholder: "Search Loai Giay ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-loai-giay',
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
    $('#searchName11').select2({
        width: 150,
        placeholder: "Search Size ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-kich-co',
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
    $('#searchName12').select2({
        width: 150,
        placeholder: "Search Mau Sac ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-mau-sac',
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
    $('#searchName13').select2({
        width: 150,
        placeholder: "Search De Giay ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-de-giay',
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
    $('#searchName14').select2({
        width: 150,
        placeholder: "Search Chat Lieu ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-chat-lieu',
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
// validate modal
//
//     $(document).ready(function () {
//         $('#exampleModal2 form').submit(function (e) {
//             e.preventDefault();
//
//             $.ajax({
//                 type: 'POST',
//                 url: '/san-pham/kich-co/add/' + sanpham.id,
//                 data: $('#exampleModal2 form').serialize(),
//                 success: function (response) {
//                     if (response.success) {
//                         // Đóng modal nếu thành công
//                         $('#exampleModal2').modal('hide');
//                         // Thực hiện các xử lý khác sau khi thêm thành công (nếu cần)
//                     } else {
//                         // Xử lý khi có lỗi
//                         if (response.errorMessage) {
//                             // Hiển thị lỗi chung (nếu có)
//                             alert(response.errorMessage);
//                         }
//                         if (response.errors) {
//                             for (var key in response.errors) {
//                                 // Hiển thị lỗi cho từng trường dựa vào key
//                                 $('#' + key + 'Error').text(response.errors[key]);
//                             }
//                         }
//                     }
//                 },
//                 error: function () {
//                     // Xử lý lỗi nếu cần
//                 }
//             });
//         });
//     });