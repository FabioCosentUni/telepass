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
<header class="masthead">
    <div class="container px-5">
        <div class="row gx-5 align-items-center">
            <div class="col-lg-6">
                <!-- Mashead text and app badges-->
                <div class="mb-5 mb-lg-0 text-center text-lg-start">
                    <h1 class="display-1 lh-1 mb-3">Rendi veloce ogni viaggio.</h1>
                    <p class="lead fw-normal text-muted mb-5">Telepass e Telepass plus, come non hai mai viaggiato.</p>
                    <div class="d-flex flex-column flex-lg-row align-items-center">
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <!-- Masthead device mockup feature-->
                <div class="masthead-device-mockup">
                    <svg class="circle" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                        <defs>
                            <linearGradient id="circleGradient" gradientTransform="rotate(45)">
                                <stop class="gradient-start-color" offset="0%"></stop>
                                <stop class="gradient-end-color" offset="100%"></stop>
                            </linearGradient>
                        </defs>
                        <circle cx="50" cy="50" r="50"></circle></svg
                    ><svg class="shape-1 d-none d-sm-block" viewBox="0 0 240.83 240.83" xmlns="http://www.w3.org/2000/svg">
                    <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(120.42 -49.88) rotate(45)"></rect>
                    <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(-49.88 120.42) rotate(-45)"></rect></svg
                ><svg class="shape-2 d-none d-sm-block" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg"><circle cx="50" cy="50" r="50"></circle></svg>
                    <div class="device-wrapper">
                        <div class="device" data-device="iPhoneX" data-orientation="portrait" data-color="black">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Quote/testimonial aside-->
<aside class="text-center bg-gradient-primary-to-secondary">
    <div class="container px-5">
        <div class="row gx-5 justify-content-center">
            <div class="col-xl-8">
                <div class="h2 fs-1 text-white mb-4">Abbonarsi e' semplice, basta un click! Prova subito</div>
                <img src="${pageContext.request.contextPath}/img/telepass_logo.svg" alt="..." style="height: 3rem" />
            </div>
        </div>
    </div>
</aside>
<!-- App features section-->
<section id="features">
    <div class="container px-5">
        <div class="row gx-5 align-items-center">
            <div class="col-lg-8 order-lg-1 mb-5 mb-lg-0">
                <div class="container-fluid px-5">
                    <div class="row gx-5">
                        <div class="col-md-6 mb-5">
                            <!-- Feature item-->
                            <div class="text-center">
                                <i class="bi-phone icon-feature text-gradient d-block mb-3"></i>
                                <h3 class="font-alt">Mobile app</h3>
                                <p class="text-muted mb-0">Per avere sempre con te il tuo dispositivo di fiducia.</p>
                            </div>
                        </div>
                        <div class="col-md-6 mb-5">
                            <!-- Feature item-->
                            <div class="text-center">
                                <i class="bi bi-patch-check icon-feature text-gradient d-block mb-3"></i>
                                <h3 class="font-alt">Sicurezza</h3>
                                <p class="text-muted mb-0">Viaggia sicuro e senza pensieri, ci pensiamo noi.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-5 mb-md-0">
                            <!-- Feature item-->
                            <div class="text-center">
                                <i class="bi-gift icon-feature text-gradient d-block mb-3"></i>
                                <h3 class="font-alt">Regali mensili</h3>
                                <p class="text-muted mb-0">Ogni mese una sopresa speciale!</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!-- Feature item-->
                            <div class="text-center">
                                <i class="bi bi-bar-chart icon-feature text-gradient d-block mb-3"></i>
                                <h3 class="font-alt">Statistiche</h3>
                                <p class="text-muted mb-0">Tieni traccia dei tuoi pagamenti e percorsi!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 order-lg-0">
                <!-- Features section device mockup-->
                <div class="features-device-mockup">
                    <svg class="circle" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                        <defs>
                            <linearGradient id="circleGradient" gradientTransform="rotate(45)">
                                <stop class="gradient-start-color" offset="0%"></stop>
                                <stop class="gradient-end-color" offset="100%"></stop>
                            </linearGradient>
                        </defs>
                        <circle cx="50" cy="50" r="50"></circle></svg
                    ><svg class="shape-1 d-none d-sm-block" viewBox="0 0 240.83 240.83" xmlns="http://www.w3.org/2000/svg">
                    <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(120.42 -49.88) rotate(45)"></rect>
                    <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(-49.88 120.42) rotate(-45)"></rect></svg
                ><svg class="shape-2 d-none d-sm-block" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg"><circle cx="50" cy="50" r="50"></circle></svg>
                    <div class="device-wrapper">
                        <div class="device" data-device="iPhoneX" data-orientation="portrait" data-color="black">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Call to action section-->
<section class="cta">
    <div class="cta-content">
        <div class="container px-5">
            <h2 class="text-white display-1 lh-1 mb-4">
                Stop waiting.
                <br />
                Start fast.
            </h2>
        </div>
    </div>
</section>
<!-- App badge section-->
<section class="bg-gradient-primary-to-secondary" id="download">
    <div class="container px-5">
        <h2 class="text-center text-white font-alt mb-4">Get the app now!</h2>
        <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
            <a target=”_blank” class="me-lg-3 mb-4 mb-lg-0" href="https://play.google.com/store/apps/details?id=uk.co.novaware.telepass.android&hl=it&gl=US&pli=1"><img class="app-badge" src="${pageContext.request.contextPath}/img/google-play-badge.svg" alt="..." /></a>
            <a target=”_blank” href="https://apps.apple.com/it/app/telepass/id454224356"><img class="app-badge" src="${pageContext.request.contextPath}/img/app-store-badge.svg" alt="..." /></a>
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
