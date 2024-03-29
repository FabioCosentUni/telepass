<%@ page import="exception.TelepassError" %><%--
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

<section id="register" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-5 col-lg-6 col-xl-5">
                <img src="${pageContext.request.contextPath}/img/payment.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                <%if (request.getAttribute("error") != null) {%>
                    <div class="alert alert-danger" role="alert">
                        <p class="text-center"><%=((TelepassError)request.getAttribute("error")).getErrorMessage()%></p>
                    </div>
                <%}%>
                <form action="register" method="POST" id="formSignup" onsubmit="return validateRequest()">

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Metodo di pagamento</p>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="nome_assign" type="text" placeholder="AA111AA" name="nome_prp" value="<%=request.getAttribute("nome_prp") != null ? request.getAttribute("nome_prp") : ""%>" required oninput=""/>
                        <label for="nome_assign">Nome</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="cognome_assign" type="text" placeholder="Cognome" name="cognome_prp" value="<%=request.getAttribute("cognome_prp") != null ? request.getAttribute("cognome_prp") : ""%>" required oninput=""/>
                        <label for="cognome_assign">Cognome</label>
                        <div class="invalid-feedback" id="invalidCognome"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control" id="numero_carta_assign" type="text" placeholder="Numero_carta" name="numero_carta" value="<%=request.getAttribute("numero_carta") != null ? request.getAttribute("numero_carta") : ""%>" required/>
                        <label for="numero_carta_assign">Numero carta</label>
                        <div class="invalid-feedback" id="invalidNumeroCarta"></div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="scadenza_assign" type="date" placeholder="Scadenza" name="scadenza" value="<%=request.getAttribute("scadenza") != null ? request.getAttribute("scadenza") : ""%>" required/>
                        <label for="scadenza_assign">Scadenza</label>
                        <div class="invalid-feedback" id="invalidScadenza"></div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="cvc_assign" type="text" placeholder="Cvc" name="cvc" value="<%=request.getAttribute("cvc") != null ? request.getAttribute("cvc") : ""%>" required/>
                        <label for="cvc_assign">Cvc</label>
                        <div class="invalid-feedback" id="invalidCvc"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <p>Metodo di pagamento</p>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentOption" id="credit_card" value="0">
                            <label class="form-check-label" for="credit_card">
                                Carta di Credito
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentOption" id="bancomat" value="1" checked>
                            <label class="form-check-label" for="bancomat">
                                Bancomat
                            </label>
                        </div>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <div class="d-grid"><button class="btn btn-primary rounded-pill btn-lg disabled" id="confirmButton" type="confirm">Conferma</button></div>
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


<script src="${pageContext.request.contextPath}/js/methodPayment.js"></script>
</body>
</html>