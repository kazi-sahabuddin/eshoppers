<%--
  Created by IntelliJ IDEA.
  User: sahabuddin
  Date: 3/29/24
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>
<div class="container">
    <div class="row">
        <div class="card mt-4">

            <div class="card-body">
                <h2 class="card-title">Sign up</h2>
                <hr class="mb-4">
                <form class="form-horizontal" role="form" action="<c:url value="/signup"/>" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" value="${userDto.username}" placeholder="Username" required minlength="4" maxlength="32">
                        <c:if test="${errors.username != null}">
                            <small class="text-danger">${errors.username}</small>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${userDto.email}" placeholder="you@example.com" required>
                        <c:if test="${errors.email != null}">
                            <small class="text-danger">${errors.email}</small>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="" required>
                        <c:if test="${errors.password != null}">
                            <small class="text-danger">${errors.password}</small>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="confirmed-password">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmed-password" name="passwordConfirmed"  placeholder="" required>
                        <c:if test="${errors.passwordConfirmed != null}">
                            <small class="text-danger">${errors.passwordConfirmed}</small>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="first-name">First name</label>
                        <input type="text" class="form-control" id="first-name" name="firstName" value="${userDto.firstName}" placeholder="First name" required>
                        <c:if test="${errors.firstName != null}">
                            <small class="text-danger">${errors.firstName}</small>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="last-name">Last name</label>
                        <input type="text" class="form-control" id="last-name" name="lastName" value="${userDto.lastName}" placeholder="" required>
                        <c:if test="${errors.lastName != null}">
                            <small class="text-danger">${errors.lastName}</small>
                        </c:if>
                    </div>
                    <hr class="mb-4">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg" onclick="return validPassword()">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function validPassword(){
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("passwordConfirmed").value;

        if (password !== confirmPassword){
            alert(password + " == "+confirmPassword+"password could not match");
            return false;
        }
        return true;

    }
</script>
<%@include file="includes/footer.jsp"%>
