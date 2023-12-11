<%--
  Created by IntelliJ IDEA.
  User: fabio
  Date: 10/12/2023
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--Head--%>
<jsp:include page="head_html.jsp" />
<body>
<jsp:include page="nav.jsp" />

<section id="login" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-5 col-lg-6 col-xl-5">
                <img src="${pageContext.request.contextPath}/img/pngegg.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                <form action="login" method="POST" onsubmit="return validateRequest()">

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Sign in</p>
                    </div>

                    <c:when test="${'TP001' eq error.getErrorCode()}">
                        <c:set var="error_mail" value="${error.getErrorMessage()}"/>
                    </c:when>
                    <c:out value = "${error_mail}"/>
                    <!-- Email address input-->
                    <div class="form-floating mb-3">
                       <%-- <c:if test = "${UserError.INCORRECT_EMAIL.equals(error)}">--%>
                        <input class="form-control ${not empty error_mail ? 'is-invalid' : ''}" id="email_login" type="email" placeholder="name@example.com" name="email" />
                        <label for="email_login">Email address</label>
                        <div class="invalid-feedback" id="invalidEmail"><c:out>${error_mail}</c:out></div>
                    </div>


                    <div class="form-floating mb-3">
                        <input class="form-control" id="password" type="password" placeholder="password" name="password"/>
                        <label for="password">Enter password</label>
                        <div class="invalid-feedback">Password is required.</div>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <div class="d-grid"><button class="btn btn-primary rounded-pill btn-lg" id="loginButton" type="login">Login</button></div>
                        <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="/signup"
                                                                                          class="link-danger">Register</a></p>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />

<script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>
