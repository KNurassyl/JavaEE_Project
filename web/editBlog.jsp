<%@ page import="kz.bitlab.models.Blog" %>
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
            <form action="/editBlog" method="post">
                <%
                    Blog blog = (Blog) request.getAttribute("blog");
                    if (blog != null) {
                %>
                <div class="row">
                    <div class="col-12">
                        <label>TITLE </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="hidden" name="id" value="<%=blog.getId()%>">
                        <input type="text" class="form-control" name="titleUpdated" value="<%=blog.getTitle()%>">
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-12">
                        <label>CONTENT </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <textarea class="form-control" name="contentUpdated" rows="5"><%=blog.getContent()%></textarea>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button type="submit" class="btn btn-success">EDIT BLOG</button>

                        <button type="button" class="btn btn-danger float-right" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            DELETE BLOG
                        </button>

                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Confirm Delete</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure delete that blog?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">NO
                                        </button>
                                        <a href="/deleteBlog?id=<%=blog.getId()%>" class="btn btn-danger">YES</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                    }
                %>
            </form>
        </div>
    </div>
</div>
<%@include file="vendor/footer.jsp" %>
</body>
<%@include file="vendor/foot.jsp" %>
</html>
