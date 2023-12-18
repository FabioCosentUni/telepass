<%@ page import="exception.TelepassError" %><%--
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
<c:if test="${utente == null}">
    <jsp:forward page="login.jsp" />
</c:if>
<section id="login" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-5 col-lg-6 col-xl-5">
                <img src="${pageContext.request.contextPath}/img/gestisci.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                <table class="table table-success table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Targa</th>
                        <th scope="col">Modello</th>
                        <th scope="col">Brand</th>
                        <th scope="col">Tipologia</th>
                        <th scope="col">Colore</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="veicolo" items="${utente.getTrasponder().getVeicoloList()}">
                        <tr>
                            <td>${veicolo.targaPk}</td>
                            <td>${veicolo.modello}</td>
                            <td>${veicolo.brand}</td>
                            <td>${veicolo.tipologiaVe}</td>
                            <td>${veicolo.colore}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />

<%--<script src="${pageContext.request.contextPath}/js/login.js"></script>--%>
</body>
</html>
