<%@ page import="kz.bitlab.models.User" %><%
    User current_user = (User) request.getSession().getAttribute("CURRENT_USER");
%>
<div class="container">
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(35,67,105,0.85);">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">BITLAB BLOG</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"

                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"

                aria-expanded="false" aria-label="Toggle navigation">

            <span class="navbar-toggler-icon"></span>

        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%
                    if (current_user != null) {
                %>

                <li class="nav-item">
                    <a class="nav-link" href="/addBlog">Add Blog</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <%=current_user.getFullName()%>
                    </a>

                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a class="dropdown-item" href="/profile">Profile</a></li>

                        <li><a class="dropdown-item" href="/settings">Settings</a></li>

                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li><a class="dropdown-item" href="/logout">Logout</a></li>
                    </ul>
                </li>
                <%
                    }
                    else {
                %>

                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/register">Register</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </ul>
            <form class="d-flex" role="search" action="/searchedBlog" method="post">
                <input class="form-control me-2" type="search" placeholder="Search by title" aria-label="Search" name="search">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
            <%
                }
            %>
        </div>
    </div>
</nav>
</div>