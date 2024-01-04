<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="exception.TelepassError" %>
<%@ page import="model.Casello" %>
<%@ page import="java.util.ArrayList" %><%--
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

<section class="bg-gradient-primary-to-secondary" id="download" style="padding-bottom: 25rem">

    <div class="container px-5" style="margin-top: 10%">
        <h2 class="text-center text-white font-alt mb-4">Scegli autostrada</h2>
        <form id="autostradaForm" action="/Telepass/simulation" method="get">
            <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
                <select id="autostradaSelect" name="autostrada" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example" style="margin-bottom: 30px" onchange="onAutostradaSelectChange()">
                    <option selected id="selectedAutostrada">
                        <c:choose><c:when test="${autostradaSel != null}">${autostradaSel}</c:when><c:otherwise>Autostrada</c:otherwise></c:choose>
                    </option>
                    <c:forEach items="${autostrade}" var="autostrada">
                        <option value="${autostrada}">${autostrada}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
                <button type="submit" class="btn btn-primary btn-xl" onclick="submitForm()">Invia</button>
            </div>
        </form>
        <c:if test="${autostradaSel != null}">
        <form action="simulation" method="POST" onsubmit="return validateRequest()">
            <h2 class="text-center text-white font-alt mb-4">Scegli casello di entrata e di uscita</h2>
            <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
                <select id="entrataSelect" name="entrataSelect" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example">
                    <option selected>Entrata</option>
                    <c:forEach items="${caselli}" var="casello">
                        <c:if test="${casello.getAutostrada() eq autostradaSel}">
                            <option value="${casello.getIdCaselloPk()}">${casello.getCitta()} - ${casello.getKm()} km</option>
                        </c:if>
                    </c:forEach>
                </select>
                <select id="uscitaSelect" name="uscitaSelect" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example">
                    <option selected>Uscita</option>
                    <c:forEach items="${caselli}" var="casello">
                        <c:if test="${casello.getAutostrada() eq autostradaSel}">
                            <option value="${casello.getIdCaselloPk()}">${casello.getKm()}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
                <select id="veicoloSel" name="veicoloSel" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example">
                    <option selected>Veicolo</option>
                    <c:forEach items="${utente.getTransponder().getVeicoloList()}" var="veicolo">
                        <option value="${veicolo.getTargaPk()}">${veicolo.getTargaPk()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
                <button type="submit" class="btn btn-primary btn-xl">Esegui viaggio</button>
            </div>
        </form>
        </c:if>
    </div>
</section>


<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />


<script src="${pageContext.request.contextPath}/js/simulation.js"></script>
</body>
</html>
