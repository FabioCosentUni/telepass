<!DOCTYPE html>
<html lang="en">
<%--Head--%>
<jsp:include page="head_html.jsp" />
<body id="page-top">
<!-- Navigation-->
<jsp:include page="nav.jsp" />

<%if (request.getAttribute("error") != null && !request.getAttribute("error").toString().isEmpty()) { %>
<script>
    alert("<%= request.getAttribute("error") %>");
</script>
<% } %>
<!-- Mashead header-->
<section class="masthead">
    <div class="container px-5">
        <div class="row gx-5 align-items-center">
            <div class="col-lg-6">
                <!-- Mashead text and app badges-->
                <div class="mb-5 mb-lg-0 text-center text-lg-start">
                    <h1 class="display-1 lh-1 mb-3">TELEPASS+ </h1>
                    <p class="lead fw-normal text-muted mb-5">Telepass+ offre un'assistenza stradale H24 7 giorni su 7.</p>
                    <p class="lead fw-normal text-muted mb-5">Aggiungi Telepass+ al tuo piano, senza troppi costi aggiuntivi.</p>
                    <div class="d-flex flex-column flex-lg-row align-items-center">
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <!-- Masthead device mockup feature-->
                <div class="masthead-device-mockup">
                    <div class="d-grid p-2">
                        <form action="/Telepass/telepassPlus" method="POST">
                            <button class="btn btn-primary rounded-pill btn-lg" id="telepassPlus" type="assign">Passa a Telepass+</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Footer-->
<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />

</body>
</html>
