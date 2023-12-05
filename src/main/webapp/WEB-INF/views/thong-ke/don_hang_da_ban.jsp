<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .center-container {
        text-align: center;
        margin-top: 15px;
    }

    .center-button {
        font-size: 16px;
        background-color: #eaeaea;
        border: 2px solid #A3A3A3;
        color: #7c7c7c;
        height: 30px;
        line-height: 30px;
        border-radius: 5px;
        width: 100px;
    }
</style>
<!-- Content Row -->
<div class="row">

    <!-- Earnings (Monthly) Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <a href="/bumblebee/thong-ke/san-pham-da-ban">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Sản phẩm đã bán
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">${soSPDaBan}</div>
                        </div>
                        <div class="col-auto">
                            <img src="../../../img/3403738_football_gym_shoes_sport_training_icon.png" width="40px">
                        </div>
                    </div>
                </div>
            </a>

        </div>
    </div>

    <!-- Earnings (Monthly) Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <a href="/bumblebee/thong-ke/don-hang-da-ban">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Đơn hàng đã bán
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">${soHoaDonDaBan}</div>
                        </div>
                        <div class="col-auto">
                            <img src="../../../img/7503208_bill_invoice_receipt_icon.png" width="40px">
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>

    <!-- Earnings (Monthly) Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <a href="/bumblebee/thong-ke/san-pham-loi">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Sản phẩm lỗi
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>

    <!-- Pending Requests Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <a href="/bumblebee/thong-ke/san-pham-da-ban">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Đơn hàng bị đổi trả
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xl-12 col-lg-12">
        <!-- Bar Chart -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <div class="row">
                    <div class="col-lg-6">
                        <h6 class="m-0 font-weight-bold" style="color: #1cc88a">Đơn hàng đã bán</h6>
                    </div>
                    <div class="col-lg-6">
                        <form>
                            <div class="row">
                                <div class="col-lg-4">
                                    Từ ngày: <input type="datetime-local" class="filterDate" path="fromDate"/>
                                </div>
                                <div class="col-lg-4">
                                    Đến ngày: <input type="datetime-local" class="filterDate" path="toDate"/>
                                </div>
                                <div class="col-lg-4">
                                    <div class="center-container">
                                        <button class="center-button">Lọc</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="chart-bar">
                    <canvas id="myBarChartDonHangDaBan"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var soDonHangDaBanThang1 = ${soDonHangDaBanThang1};
    var soDonHangDaBanThang2 = ${soDonHangDaBanThang2};
    var soDonHangDaBanThang3 = ${soDonHangDaBanThang3};
    var soDonHangDaBanThang4 = ${soDonHangDaBanThang4};
    var soDonHangDaBanThang5 = ${soDonHangDaBanThang5};
    var soDonHangDaBanThang6 = ${soDonHangDaBanThang6};
    var soDonHangDaBanThang7 = ${soDonHangDaBanThang7};
    var soDonHangDaBanThang8 = ${soDonHangDaBanThang8};
    var soDonHangDaBanThang9 = ${soDonHangDaBanThang9};
    var soDonHangDaBanThang10 = ${soDonHangDaBanThang10};
    var soDonHangDaBanThang11 = ${soDonHangDaBanThang11};
    var soDonHangDaBanThang12 = ${soDonHangDaBanThang12};


</script>