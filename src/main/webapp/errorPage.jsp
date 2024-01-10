<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head_html.jsp" />
<body>
    <div class="container px-5"><%--Possibilità di ritornare alla homepage cliccando sul tasto--%>
        <a class="navbar-brand fw-bold" href="/Telepass"><h2 style="padding: 20px">Telepass</h2></a>
    </div>
    <div class="container px-5">
        <div class="alert alert-danger mt-5" role="alert">
            <h4 class="alert-heading">Errore!</h4>
            <p>${requestScope.error}</p>
            <hr>
            <p class="mb-0">Contatta l'assistenza per ulteriori informazioni.</p>
        </div>
    </div>
</body>
</html>
