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
                <img src="${pageContext.request.contextPath}/img/telepass_logo.svg"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                <form action="aggiungiTransponder" method="POST" id="formTransponder" onsubmit="">

                    <div class="form-floating mb-3">
                        <input class="form-control <%= TelepassError.TELEPASS_ALREADY_REGISTERED.equals(request.getAttribute("transponderError")) ? "is-invalid" : (request.getAttribute("success") != null ? "is-valid" : "") %>" id="codice_transponder" type="text" placeholder="Codice Transponder" name="codice_transponder" value="<%=request.getAttribute("codice_transponder") != null ? request.getAttribute("codice_transponder") : ""%>" required oninput=""/>
                        <label for="codice_transponder">Codice Transponder</label>
                        <div class="invalid-feedback" id="invalid_transponder"><%= TelepassError.TELEPASS_ALREADY_REGISTERED.equals(request.getAttribute("transponderError")) ? ((TelepassError) request.getAttribute("transponderError")).getErrorMessage() : ""%></div>
                        <div class="valid-feedback" id="valid_trasnponder"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></div>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <div class="d-grid"><button class="btn btn-primary rounded-pill btn-lg disabled" id="registerButton" type="signup">Registra Dispositivo</button></div>
                    </div>

                </form>
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

<script src="${pageContext.request.contextPath}/js/addTransponder.js"></script>
</body>
</html>
