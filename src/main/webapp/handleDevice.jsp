<%@ page import="exception.TelepassError" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--Head--%>
<jsp:include page="head_html.jsp" />
<body>
<jsp:include page="nav.jsp" />

<% if(request.getAttribute("error") != null) { %>
<section id="error" class="pb-3 d-flex justify-content-center align-items-center" style="height: 60vh;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <div class="alert alert-danger" role="alert">
                    <i class="bi bi-exclamation-triangle-fill"></i>
                    <strong>Errore!</strong> <%= ((TelepassError)request.getAttribute("error")).getErrorMessage() %>
                </div>
            </div>
        </div>
    </div>
</section>
<% } else { %>

    <section id="signup" class="pb-3">
        <div class="container-fluid h-custom">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-5 col-lg-6 col-xl-5">
                    <img src="${pageContext.request.contextPath}/img/security.png"
                         class="img-fluid" alt="Sample image">
                </div>
                <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                    <div class="text-center">
                        <a href="admin/aggiungiTransponder" class="btn btn-primary mr-2">Aggiungi un dispositivo Telepass</a>
                        <a href="admin/revocaTransponder" class="btn btn-primary">Revoca un dispositivo Telepass</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
<% } %>

<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />

<!--<script src="${pageContext.request.contextPath}/js/login.js"></script>-->
</body>
</html>
