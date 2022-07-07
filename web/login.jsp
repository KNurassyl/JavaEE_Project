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
                String emailError = request.getParameter("email_error");
                if (emailError != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Incorrect email, try again!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <%
                String passwordError = request.getParameter("password_error");
                if (passwordError != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Incorrect password, try again!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form action="/login" method="post">
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
                        <button class="btn btn-success">SIGN IN</button>
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