<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<section class="bg-gradient-primary-to-secondary" id="download" style="padding-bottom: 25rem">
    <div class="container px-5" style="margin-top: 10%">
        <h2 class="text-center text-white font-alt mb-4">Scegli autostrada</h2>
        <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
            <select id="autostradaSelect" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example" style="margin-bottom: 30px">
                <option selected>Autostrada</option>
                <c:forEach items="${autostrade}" var="autostrada">
                    <option value="${autostrada}">${autostrada}</option>
                </c:forEach>
            </select>
        </div>
        <h2 class="text-center text-white font-alt mb-4">Scegli casello di entrata e di uscita</h2>
        <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
            <select id="entrataSelect" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example">
                <option selected>Entrata</option>
                <%--<option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>--%>
            </select>
            <select id="uscitaSelect" class="form-select-font-family form-select-sm" aria-label=".form-select-sm example">
                <option selected>Uscita</option>
                <%--<option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>--%>
            </select>
        </div>
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
