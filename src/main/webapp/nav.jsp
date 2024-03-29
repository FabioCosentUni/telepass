<%@ page import="model.Utente" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fabio
  Date: 10/12/2023
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-light fixed-top shadow-sm" id="mainNav">
    <div class="container px-5">
        <a class="navbar-brand fw-bold" href="/Telepass">Telepass</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="bi-list"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0">
                <c:choose>
                    <c:when test="${utente == null}">
                        <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/signup">Abbonati</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/login">Login</a></li>
                    </c:when>
                    <c:when test="${utente != null}">
                        <c:choose>
                        <c:when test="${utente.getAmministratore() == 1}">
                            <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/admin">Gestione Dispositivi</a></li>
                            <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/logout">Logout</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/simulation">Simulazione</a></li>
                            <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/viewTransponder">I miei viaggi</a></li>
                            <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/gestisciAbb">Gestisci abbonamento</a></li>
                            <li class="nav-item"><a class="nav-link me-lg-3" href="/Telepass/logout">Logout</a></li>

                        </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </ul>
            <% if(request.getSession().getAttribute("utente") != null && ((Utente)request.getSession().getAttribute("utente")).getAmministratore() == 0){ %>
            <button class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0" data-bs-toggle="modal" data-bs-target="#feedbackModal">
                        <span class="d-flex align-items-center">
                            <i class="bi-chat-text-fill me-2"></i>
                            <span class="small">Helpdesk</span>
                        </span>
            </button>
            <% } %>
        </div>
    </div>
</nav>
