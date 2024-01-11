<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head_html.jsp" />
<body>
<jsp:include page="nav.jsp" />
    <%--<div class="container px-5">&lt;%&ndash;Possibilità di ritornare alla homepage cliccando sul tasto&ndash;%&gt;
        <a class="navbar-brand fw-bold" href="/Telepass"><h2 style="padding: 20px">Telepass</h2></a>
    </div>--%>
    <div class="container px-5" >
        <div class="alert alert-danger mt-6" role="alert">
            <h4 class="alert-heading">Attenzione!</h4>
            <p>${requestScope.error}</p>
            <hr>
            <p class="mb-0">Si è verificato un errore imprevisto. Contatta l'assistenza per ulteriori informazioni.</p>
        </div>
    </div>

<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />
</body>
</html>
