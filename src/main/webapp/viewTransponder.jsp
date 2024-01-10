<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="exception.TelepassError" %>
<%@ page import="model.Utente" %>
<%-- Created by IntelliJ IDEA.
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
                <ul class="nav nav-tabs" id="myTabs">
                    <c:set var="i" value="1"/>
                    <c:forEach items="${viaggiMap.keySet()}" var="veicolo">
                        <li class="nav-item" style="--bs-nav-link-color: black">
                            <a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab${i}">${veicolo.getTargaPk()}</a>
                        </li>
                        <c:set var="i" value="${i+1}"/>
                    </c:forEach>
                </ul>
                <c:if test="${viaggiMap.isEmpty()}">
                    <tr>
                        <td colspan="2">Non hai ancora effettuato viaggi</td>
                    </tr>
                </c:if>

                <div class="tab-content mt-2">
                    <c:if test="${not viaggiMap.isEmpty()}">
                        <c:set var="x" value="1"/>
                        <c:forEach var="viaggi" items="${viaggiMap.values()}">
                            <div class="tab-pane fade show active" id="tab${x}">
                                <table class="table table-success table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">Casello entrata</th>
                                        <th scope="col">Data entrata</th>
                                        <th scope="col">Casello uscita</th>
                                        <th scope="col">Data uscita</th>
                                        <th scope="col">Pedaggio</th>
                                    </tr>
                                    </thead>
                                    <c:forEach var="v" items="${viaggi}">
                                    <tbody>
                                        <tr>
                                            <td>${v.getCaselloEntryDTO().getCitta()}</td>
                                            <td>${v.getFormatDateEntry()}</td>
                                            <td>${v.getCaselloExitDTO().getCitta()}</td>
                                            <td>${v.getFormatDateExit()}</td>
                                            <td>€ ${v.getPedaggio()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <c:set var="x" value="${x+1}"/>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <% } %>
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
