<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <h1 style="text-align: center">Danh sách hóa đơn đổi trả</h1>
    <div class="row">
        <div class="col-lg-6">
            <button type="button" id="openCam" onclick="openWebcam()" class="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop">
                <i class="bi bi-qr-code-scan"></i>
                Quét QR
            </button>
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="vid">
                                <video
                                        style="border: 1px solid"
                                        id="camHoaDon"
                                        autoplay="camHoaDon"
                                        width="270px"
                                        height="150px"
                                ></video>
                            </div>
                            <div id="output"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="closeCam" onclick="closeWebcam()"
                                    class="btn btn-secondary"
                                    data-bs-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control border-0 small" placeholder="Tìm kiếm hóa đơn đổi trả"
                       aria-label="Search" aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="status">
        <ul class="nav justify-content-center bg-gradient-light nav-pills">
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'all' ? 'active' : ''}" aria-current="page"
                   href="/bumblebee/doi-hang/list-tra-hang">Danh sách trả hàng
                    <span class="badge text-bg-secondary">${countHD}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'cho-xac-nhan' ? 'active' : ''}"
                   href="/bumblebee/doi-hang/list-doi-hang">Danh sách đổi hàng<span
                        class="badge text-bg-secondary">${countHDCho}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'cho-xac-nhan' ? 'active' : ''}"
                   href="/bumblebee/doi-hang/list-doi-hang">Danh sách sản phẩm lỗi<span
                        class="badge text-bg-secondary">${countHDCho}</span></a>
            </li>
        </ul>
    </div>
    <br>
    <div class="content-listDoiTra">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Màu sắc</th>
                <th scope="col">Kích cỡ</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Nhân viên thực hiện</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach varStatus="i" items="${listDoiTra}" var="dt">
                <tr>
                    <td>${i.count}</td>
                    <td>${dt.chiTietSanPham.sanPham.tenSanPham}</td>
                    <td>${dt.chiTietSanPham.mauSac.ten}</td>
                    <td>${dt.chiTietSanPham.kichCo.size}</td>
                    <td>${dt.soLuong}</td>
                    <td><fmt:formatNumber value="${dt.donGia}" type="number"/></td>
                    <td>${dt.doiTra.nhanVien.ho} ${dt.doiTra.nhanVien.tenDem} ${dt.doiTra.nhanVien.ten}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div>
        <nav aria-label="Page navigation example" >
            <ul class="pagination" style="justify-content: center">
                <li class="page-item"><a class="page-link" href=/bumblebee/doi-hang/list-doi-hang?page=${page.number-1}">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="/bumblebee/doi-hang/list-doi-hang?page=${page.number+1}">Next</a></li>
            </ul>
        </nav>
    </div>
</div>
