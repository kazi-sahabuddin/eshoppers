<%--
  Created by IntelliJ IDEA.
  User: sahabuddin
  Date: 3/28/24
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="sec" uri="http://sahabuddin.com/functions" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/home"/>">e-Shoppers</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/home"/>">Home</a>
                </li>

                <c:if test="${!sec:isAuthenticated(pageContext.request)}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/signup"/>"> Sign up</a>
                    </li>
                </c:if>

                <li class="nav-item">
                <c:choose>
                    <c:when test="${sec:isAuthenticated(pageContext.request)}">
                        <a class="nav-link" href="#" onclick="logout()">Logout[${sec:getCurrentUser(pageContext.request).firstName}]</a>
                        <form style="visibility: hidden" action="<c:url value="/logout"/> " id="logoutForm" method="post"></form>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="<c:url value="/login"/> "> Log In</a>
                    </c:otherwise>
                </c:choose>
                </li>



                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script>
    function logout(){
        document.getElementById("logoutForm").submit();
    }
</script>