<%@ page import="exception.TelepassError" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--Head--%>
<jsp:include page="head_html.jsp" />
<body>
<jsp:include page="nav.jsp" />

<section id="assignVehicle" class="pb-3">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-5 col-lg-6 col-xl-5">
                <img src="${pageContext.request.contextPath}/img/vehicleAssign.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-6 col-xl-4 offset-xl-1 pt-5">
                <form action="assignVehicle" method="POST" id="formSignup" onsubmit="return validateRequest()">

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Step 2 - Associa un veicolo al tuo Telepass</p>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control <%= TelepassError.VEHICLE_ALREADY_REGISTERED.equals(request.getAttribute("error")) ? "is-invalid" : "" %>" id="targa_assign" type="text" placeholder="AA111AA" name="targa_veicolo" value="<%=request.getAttribute("targa_veicolo") != null ? request.getAttribute("targa_veicolo") : ""%>" required oninput=""/>
                        <label for="targa_assign">Targa</label>
                        <div class="invalid-feedback" id="invalidTarga"><%= TelepassError.VEHICLE_ALREADY_REGISTERED.equals(request.getAttribute("error")) ? ((TelepassError) request.getAttribute("error")).getErrorMessage() : ""%></div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="tipologia_assign" type="text" placeholder="Tipologia" name="tipologia" value="<%=request.getAttribute("tipologia") != null ? request.getAttribute("tipologia") : ""%>" required/>
                        <label for="tipologia_assign">Tipologia</label>
                        <div class="invalid-feedback" id="invalidTipologia"></div>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <div class="d-grid"><button class="btn btn-primary rounded-pill btn-lg disabled" id="assignButton" type="assign">Avanti</button></div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />


<script src="${pageContext.request.contextPath}/js/assignVehicle.js"></script>
</body>
</html>