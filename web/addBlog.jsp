<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <%@include file="vendor/head.jsp" %>

</head>

<body>

<%@include file="vendor/header.jsp" %>

<div class="container" style="min-height: 640px;">
    <div class="row mt-3">
        <div class="col-8 mx-auto">
            <%
                String success = request.getParameter("success");
                if (success != null) {
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Blog added successfully!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <%
                String error = request.getParameter("error");
                if (error != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Error on adding blog!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form action="/addBlog" method="post">
                <div class="row">
                    <div class="col-12">
                        <label>TITLE </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="title" placeholder="Title">
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-12">
                        <label>CONTENT </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <textarea class="form-control" name="content" rows="5" placeholder="Content"></textarea>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button class="btn btn-success">ADD BLOG</button>
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
