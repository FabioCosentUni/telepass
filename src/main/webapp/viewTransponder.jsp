<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="exception.TelepassError" %>
<%@ page import="model.Utente" %>
<%@ page import="model.Viaggio" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Veicolo" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--Head--%>
<jsp:include page="head_html.jsp" />
<body>
<jsp:include page="nav.jsp" />

<section id="gestisciAbb" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-4 col-lg-4 col-xl-4">
                <img src="${pageContext.request.contextPath}/img/gestisci.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-7 col-xl-7 offset-xl-1 pt-0">
                <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>Errore!</strong> <%=((TelepassError)request.getAttribute("error")).getErrorMessage()%>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <% } else {%>
                <h3 class="text-center pb-3">Viaggi effettuati</h3>
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <% if(!((Map<Veicolo, List<Viaggio>>)request.getAttribute("viaggiMap")).isEmpty()) { %>
                    <% int x = 0; %>
                    <% for(Veicolo v : ((Map<Veicolo, List<Viaggio>>)request.getAttribute("viaggiMap")).keySet()) { %>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link <%= x==0 ? "active" : ""%>" id="<%=v.getTargaPk()%>-tab" data-bs-toggle="tab" data-bs-target="#<%=v.getTargaPk()%>" type="button" role="tab" aria-controls="home" aria-selected="true"><%=v.getTargaPk()%></button>
                    </li>
                    <% x++; } %>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <%int i = 0; %>
                    <% for(Veicolo v : ((Map<Veicolo, List<Viaggio>>)request.getAttribute("viaggiMap")).keySet()) { %>
                    <div class="tab-pane fade <%=i==0 ? "show active" : ""%>" id="<%=v.getTargaPk()%>" role="tabpanel" aria-labelledby="<%=v.getTargaPk()%>-tab">
                        <table class="table table-success table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Casello entrata</th>
                                <th scope="col">Data entrata</th>
                                <th scope="col">Casello uscita</th>
                                <th scope="col">Data uscita</th>
                                <th scope="col">Km percorsi</th>
                                <th scope="col">Pedaggio pagato</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for(Viaggio viaggio : ((Map<Veicolo, List<Viaggio>>)request.getAttribute("viaggiMap")).get(v)) { %>
                            <tr>
                                <td><%=viaggio.getCaselloEntryDTO().getCitta()%></td>
                                <td><%=viaggio.getFormatDateEntry()%></td>
                                <td><%=viaggio.getCaselloExitDTO().getCitta()%></td>
                                <td><%=viaggio.getFormatDateExit()%></td>
                                <td><%=Math.abs(viaggio.getCaselloExitDTO().getKm()-viaggio.getCaselloEntryDTO().getKm())%></td>
                                <td>€ <%=viaggio.getPedaggio()%></td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>

                    <% i++; } %>
                </div>
                <% } %>
                <% } %>
            </div>
            </div>
        </div>
    </div>
</section>



<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />


<script>
    $(document).ready(function(){
        // Attiva le schede Bootstrap
        $('#myTabs a').on('click', function (e) {
            e.preventDefault()
            $(this).tab('show')
        })
    });
</script>
</body>
</html>
