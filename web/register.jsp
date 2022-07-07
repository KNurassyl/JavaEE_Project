<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="vendor/head.jsp" %>
</head>
<body>
<%@include file="vendor/header.jsp" %>
<div class="container" style="min-height: 640px;">
    <div class="row mt-3">
        <div class="col-6 mx-auto">
            <%
                String user_exist = request.getParameter("user_exist");
                if (user_exist != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                User is already exist!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <%
                String email_exist = request.getParameter("email_exist");
                if (email_exist != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                That email is exist! Please try to register with another email.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>


            <%
                String password_isNotSame = request.getParameter("password_isNotSame");
                if (password_isNotSame != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Passwords are not same
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <form action="/register" method="post">
                <div class="row">
                    <div class="col-12">
                        <label>FULL NAME</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" required placeholder="Full name" name="full_name">
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <label>EMAIL</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="email" class="form-control" required placeholder="Email" name="email">
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-12">
                        <label>PASSWORD</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="password" class="form-control" required placeholder="Password" name="password">
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-12">
                        <label>PASSWORD AGAIN</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="password" class="form-control" required placeholder="Enter password again" name="re_password">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button class="btn btn-success">REGISTER</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="vendor/footer.jsp" %>
</body>
<%@include file="vendor/foot.jsp" %>
</html>