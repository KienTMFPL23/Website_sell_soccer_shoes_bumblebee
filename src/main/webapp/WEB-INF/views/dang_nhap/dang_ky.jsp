<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>SB Admin 2 - Login</title>

    <link href="../../../css/sb-admin-2.min.css" rel="stylesheet" />
</head>

<body class="bg-gradient-primary" style="background-color: #ffffff">
<div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block">
                            <img
                                    src="../../../img/logoBumblebee.png"
                                    height="500"
                                    width="470"
                                    alt=""
                            />
                        </div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1
                                            class="h4 text-gray-900 mb-4"
                                            style="margin-top: -20px"
                                    >
                                        Bumblebee Shop!
                                    </h1>
                                </div>
                                <form:form
                                        action="/bumblebee/register"
                                        class="user"
                                        modelAttribute="taikhoan"
                                        method="post"
                                >

                                    <div class="form-group">
                                        <form:input
                                                type="text"
                                                class="form-control form-control-user"
                                                placeholder="Tên đăng nhập"
                                                path="username"
                                        />
                                        <form:errors path="username" cssStyle="color: crimson"></form:errors>
                                    </div>
                                    <div class="form-group">
                                        <form:input
                                                type="password"
                                                class="form-control form-control-user"
                                                placeholder="Mật khẩu"
                                                path="password"
                                        />
                                        <form:errors path="password" cssStyle="color: crimson"></form:errors>

                                    </div>
                                    <div class="form-group">
                                        <input
                                                type="password"
                                                class="form-control form-control-user"
                                                placeholder="Nhập lại mật khẩu"
                                                name="confirmpassword"
                                        />
                                        <p style="color: crimson; font-size: 16px;">${messageConfirmPass}</p>
                                    </div>
                                    <button
                                            type="submit"
                                            class="btn btn-primary btn-user btn-block"
                                    >
                                        Đăng ký
                                    </button>
                                </form:form>
                                    <hr />
                                <div class="text-center">
                                    <a class="small" href="/bumblebee/login">Bạn đã có tài khoản? Đăng nhập!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
