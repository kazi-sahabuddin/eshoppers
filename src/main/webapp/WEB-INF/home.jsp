<%--
  Created by IntelliJ IDEA.
  User: sahabuddin
  Date: 3/27/24
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="sec" uri="http://sahabuddin.com/functions" %>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>
<div class="container">
    <div class="row">
        <div class="p-5 bg-secondary text-white">
            <c:if test="${sec:isAuthenticated(pageContext.request)}">
            <h1>Hello <c:out value="${sec:getCurrentUser(pageContext.request).firstName}"/>
                Welcome to e-shoppers!</h1>
            </c:if>
<%--            <img src="<c:url value="/image/cart.jpg"/>" style="height: 200px"--%>
<%--                 alt="cart"/>--%>
        </div>
    </div>
</div>
<div class="container">
    <div class="row mt-4">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-4">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${product.name}"/></h5>
                        <p class="card-text"><c:out value="${product.description}"/></p>
                        <p class="card-text">Price: $<c:out value="${product.price}"/></p>
                        <a class="card-link btn btn-outline-info" onclick="addToCart(${product.id})">Add to Cart</a>
                        <form style="visibility: hidden"
                            id="addToCart_${product.id}"
                              method="post"
                              action="${pageContext.request.contextPath}/add-to-cart?productId=${product.id}"
                        >
                        </form>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
</div>

<%@include file="includes/footer.jsp"%>

<script>
    function addToCart(productId){
        const form = document.getElementById("addToCart_"+productId);
        form.submit();
    }
</script>
