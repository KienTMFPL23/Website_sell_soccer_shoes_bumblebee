<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <h1 style="text-align: center">Danh sách hóa đơn đổi hàng</h1>
    <div class="row">
        <div class="col-lg-6">
            <a href="/bumblebee/doi-hang" class="btn btn-success">Đổi hàng</a>
        </div>
        <div class="col-lg-6">

        </div>
    </div>
    <br>
    <div class="status">
        <ul class="nav nav-pills nav-fill gap-2 p-1 small  rounded-5 shadow-sm" id="pillNav2" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-choxacnhan-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-choxacnhan" type="button" role="tab" aria-controls="pills-choxacnhan"
                        aria-selected="false">Chờ xác nhận
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-chuanbi-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-chuanbi"
                        type="button" role="tab" aria-controls="pills-chuanbi" aria-selected="false">Đang chuẩn bị
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-danggiao-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-danggiao"
                        type="button" role="tab" aria-controls="pills-danggiao" aria-selected="false">Đang giao
                </button>
            </li>
        </ul>
    </div>
</div>