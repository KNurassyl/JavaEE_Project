<%@ page import="kz.bitlab.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="vendor/head.jsp" %>
</head>
<body>
<%@include file="vendor/header.jsp" %>
<div class="container" style="min-height: 640px;">
    <div class="row mt-3">
        <div class="col-12">
            <%
                ArrayList<Blog> blogs = (ArrayList<Blog>) request.getAttribute("blogsSearched");
                if(blogs!=null){
                    for(Blog blog : blogs){
            %>
            <div class="row mt-3">
                <div class="col-11 mx-auto p-3" style="background-color: bisque">
                    <a href="/readBlog?id=<%=blog.getId()%>" class="text-dark text-decoration-none" style="font-size: 30px;">
                        <%=blog.getTitle()%>
                    </a>
                    <p class="mt-2"><%=blog.getContent()%></p>
                    <p class="mt-2">
                        Posted by <strong><%=blog.getUser().getFullName()%></strong>
                        at <strong><%=blog.getPostDate()%></strong>
                    </p>
                    <%
                        if (current_user != null) {
                            if (current_user.getId().equals(blog.getUser().getId())) {
                    %>
                    <a href="/editBlog?id=<%=blog.getId()%>" class="btn btn-success">EDIT</a>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <%
                    }
                }

                if (blogs.size() == 0) {
            %>
            <div class="row mt-3">
                <div class="col-11 mx-auto p-3">
                    <h2>No blogs with this title</h2>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<%@include file="vendor/footer.jsp" %>
</body>
<%@include file="vendor/foot.jsp" %>
</html>
