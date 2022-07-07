<%@ page import="kz.bitlab.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.models.Comment" %>
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
                Blog blog = (Blog) request.getAttribute("blog");
                if (blog != null) {
            %>
            <div class="row mt-3">
                <div class="col-11 mx-auto p-3" style="background-color: bisque;">
                    <h2><%=blog.getTitle()%>
                    </h2>
                    <p class="mt-2"><%=blog.getContent()%>
                    </p>
                    <p class="mt-2">
                        Posted by <strong><%=blog.getUser().getFullName()%>
                    </strong>
                        at <strong><%=blog.getPostDate()%>
                    </strong>
                    </p>
                    <hr>
                    <%
                        if (current_user != null) {
                    %>
                    <form action="/addComment" method="post">
                        <input type="hidden" name="blogId" value="<%=blog.getId()%>">
                        <textarea class="form-control mt-2" name="commentText" rows="2"
                                  placeholder="Write a comment"></textarea>
                        <button type="submit" class="btn btn-success mt-2 mb-2">ADD COMMENT</button>
                    </form>
                    <%
                        }
                    %>
                    <%
                        ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                        if (comments != null) {
                            for (Comment comment : comments) {
                    %>
                    <h4><%=comment.getUser().getFullName()%>
                    </h4>
                    <p class="mt-2"><%=comment.getComment()%>
                    </p>
                    <p class="mt-2">
                        At <strong><%=comment.getPostDate()%>
                    </strong>
                    </p>
                    <%
                            }
                        }
                        if (comments.size() == 0){
                    %>
                    <h4>There is no comment yet!</h4>
                    <%
                        }
                    %>
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