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
                    <li class="nav-item" style="--bs-nav-link-color: black">
                        <a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab1">Scheda 1</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="tab2-tab" data-toggle="tab" href="#tab2">Scheda 2</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="tab3-tab" data-toggle="tab" href="#tab3">Scheda 3</a>
                    </li>
                    <!-- Aggiungi ulteriori schede qui se necessario -->
                </ul>

                <div class="tab-content mt-2">
                    <div class="tab-pane fade show active" id="tab1">
                        <h3>Contenuto Scheda 1</h3>
                        <p>Testo o contenuto della scheda 1 qui...</p>
                    </div>
                    <div class="tab-pane fade" id="tab2">
                        <h3>Contenuto Scheda 2</h3>
                        <p>Testo o contenuto della scheda 2 qui...</p>
                    </div>
                    <div class="tab-pane fade" id="tab3">
                        <h3>Contenuto Scheda 3</h3>
                        <p>Testo o contenuto della scheda 3 qui...</p>
                    </div>
                    <!-- Aggiungi ulteriori contenuti delle schede qui se necessario -->
                </div>
            </div>
        </div>
    </div>
    <% } %>
</section>
<%--<section class="bg-gradient-primary-to-secondary" id="download" style="padding-bottom: 25rem">

    <div class="container px-5" style="margin-top: 10%">
        <ul class="nav nav-tabs" id="myTabs">
            <li class="nav-item" style="--bs-nav-link-color: black">
                <a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab1">Scheda 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="tab2-tab" data-toggle="tab" href="#tab2">Scheda 2</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="tab3-tab" data-toggle="tab" href="#tab3">Scheda 3</a>
            </li>
            <!-- Aggiungi ulteriori schede qui se necessario -->
        </ul>

        <div class="tab-content mt-2">
            <div class="tab-pane fade show active" id="tab1">
                <h3>Contenuto Scheda 1</h3>
                <p>Testo o contenuto della scheda 1 qui...</p>
            </div>
            <div class="tab-pane fade" id="tab2">
                <h3>Contenuto Scheda 2</h3>
                <p>Testo o contenuto della scheda 2 qui...</p>
            </div>
            <div class="tab-pane fade" id="tab3">
                <h3>Contenuto Scheda 3</h3>
                <p>Testo o contenuto della scheda 3 qui...</p>
            </div>
            <!-- Aggiungi ulteriori contenuti delle schede qui se necessario -->
        </div>
    </div>
</section>--%>




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
