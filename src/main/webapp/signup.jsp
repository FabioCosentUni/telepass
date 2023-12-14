<%@ page import="exception.user.UserError" %><%--
  Created by IntelliJ IDEA.
  User: fabio
  Date: 12/12/2023
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--Head--%>
<jsp:include page="head_html.jsp" />
<body>
<jsp:include page="nav.jsp" />

<section id="signup" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-5 col-lg-6 col-xl-5">
                <img src="${pageContext.request.contextPath}/img/pngegg.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                <form action="signup" method="POST" id="formSignup" onsubmit="return validateRequest()">

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Sign up</p>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control <%= UserError.USER_ALREADY_REGISTERED.equals(request.getAttribute("error")) ? "is-invalid" : "" %>" id="cf_signup" type="text" placeholder="Codice Fiscale" name="codice_fiscale" value="<%=request.getAttribute("codice_fiscale") != null ? request.getAttribute("codice_fiscale") : ""%>" required oninput=""/>
                        <label for="cf_signup">Codice Fiscale</label>
                        <div class="invalid-feedback" id="invalidCF"><%= UserError.USER_ALREADY_REGISTERED.equals(request.getAttribute("error")) ? ((UserError) request.getAttribute("error")).getErrorMessage() : ""%></div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="name_signup" type="text" placeholder="Name" name="name" value="<%=request.getAttribute("name") != null ? request.getAttribute("name") : ""%>" required oninput=""/>
                        <label for="name_signup">Name</label>
                        <div class="invalid-feedback" id="invalidName"></div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="surname_signup" type="text" placeholder="Surname" name="surname" value="<%=request.getAttribute("surname") != null ? request.getAttribute("surname") : ""%>" required/>
                        <label for="surname_signup">Surname</label>
                        <div class="invalid-feedback" id="invalidSurname"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control" id="address_signup" type="text" placeholder="Address" name="address" value="<%=request.getAttribute("address") != null ? request.getAttribute("address") : ""%>" required/>
                        <label for="address_signup">Address</label>
                        <div class="invalid-feedback" id="invalidAddress"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control" id="city_signup" type="text" placeholder="City" name="city" value="<%=request.getAttribute("city") != null ? request.getAttribute("city") : ""%>" required/>
                        <label for="city_signup">City</label>
                        <div class="invalid-feedback" id="invalidCity"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control" id="region_signup" type="text" placeholder="Region" name="region" value="<%=request.getAttribute("region") != null ? request.getAttribute("region") : ""%>" required/>
                        <label for="region_signup">Region</label>
                        <div class="invalid-feedback" id="invalidRegion"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control <%= UserError.USER_EMAIL_ALREADY_REGISTERED.equals(request.getAttribute("error")) ? "is-invalid" : "" %>" id="email_signup" type="email" placeholder="Email" name="email" value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>" required oninput=""/>
                        <label for="email_signup">Email</label>
                        <div class="invalid-feedback" id="invalidEmail"><%= UserError.USER_EMAIL_ALREADY_REGISTERED.equals(request.getAttribute("error")) ? ((UserError) request.getAttribute("error")).getErrorMessage() : ""%></div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="password" type="password" placeholder="password" name="password" value="<%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%>" required/>
                        <label for="password">Enter password</label>
                        <div class="invalid-feedback"><%= UserError.INCORRECT_PASSWORD.equals(request.getAttribute("error")) ? ((UserError) request.getAttribute("error")).getErrorMessage() : ""%></div>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <div class="d-grid"><button class="btn btn-primary rounded-pill btn-lg disabled" id="registerButton" type="signup">Submit</button></div>
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


<script src="${pageContext.request.contextPath}/js/signup.js"></script>
</body>
</html>