<%@ page import="exception.TelepassError" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
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
<section id="gestisciAbb" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-4 col-lg-4 col-xl-4">
                <img src="${pageContext.request.contextPath}/img/gestisci.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-7 col-xl-7 offset-xl-1 pt-5">
                <table class="table table-success table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Targa</th>
                        <th scope="col">Tipologia</th>
                        <th scope="col">Pedaggi pagati</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${utente.getTransponder().getVeicoloList().size() == 0}">
                        <tr>
                            <td colspan="2">Non hai ancora registrato nessun veicolo</td>
                        </tr>
                    </c:if>
                    <c:forEach var="veicolo" items="${pedaggiViaggi}">
                        <tr>
                            <td>${veicolo.get1st().getTargaPk()}</td>
                            <td>${veicolo.get1st().getTipologiaVe()}</td>
                            <td>${veicolo.get2nd().intValue()}</td>
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
</body>
</html>
