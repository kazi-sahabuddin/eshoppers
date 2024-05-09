<%--
  Created by IntelliJ IDEA.
  User: sahabuddin
  Date: 4/23/24
  Time: 7:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>
<div class="container">
    <div class="row">
        <div class="card mt-4">

            <div class="card-body">
                <h2 class="card-title">Log in</h2>
                <hr class="mb-4">

                <div class="row">
                    <c:if test="${message!=null}">
                        <div class="alert alert-success">
                                ${message}
                        </div>
                    </c:if>
                </div>

                <form class="form-horizontal" role="form" action="<c:url value="/login"/>" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username"  placeholder="Username">
                        <c:if test="${errors.username !=null}">
                            <small class="text-danger"> ${errors.username}</small>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                        <c:if test="${errors.password != null}">
                            <small class="text-danger">${errors.password}</small>
                        </c:if>
                    </div>

                    <hr class="mb-4">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg">Login</button>
                    </div>
                    <span> Don't have a user account?
            <a class="btn-link" href="<c:url value="/signup"/>">SignUp </a></span>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp"%>