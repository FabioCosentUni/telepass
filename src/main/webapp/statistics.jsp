<%@ page import="exception.TelepassError" %>
<%@ page import="model.Transponder" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Casello" %>
<%@ page import="model.bo.StatisticsBO" %>
<%@ page import="java.util.Map" %>
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
<section id="statistics" class="py-5 d-flex justify-content-center align-items-center" style="margin: 100 auto">
    <div class="container h-custom">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <h2>Statistiche Entrata/Uscita Caselli</h2>
                <%if(request.getAttribute("statistics") != null) { %>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Città</th>
                            <th>Km</th>
                            <th>% Entrata</th>
                            <th>% Uscita</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%-- Loop attraverso i transponder qui --%>
                        <% for (Casello c : ((Map<Casello, StatisticsBO>)request.getAttribute("statistics")).keySet() ) { %>
                        <tr>
                            <td><%= c.getIdCaselloPk() %></td>
                            <td><%= c.getCitta() %></td>
                            <td><%= c.getKm() %></td>
                            <td><%= ((Map<Casello, StatisticsBO>)request.getAttribute("statistics")).get(c).getEntryPercentage() %></td>
                            <td><%= ((Map<Casello, StatisticsBO>)request.getAttribute("statistics")).get(c).getExitPercentage() %></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</section>
<% } %>

<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />

<script>
</script>

</body>
</html>
