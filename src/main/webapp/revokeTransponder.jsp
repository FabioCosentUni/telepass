<%@ page import="exception.TelepassError" %>
<%@ page import="model.Transponder" %>
<%@ page import="java.util.List" %>
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
<section id="transponders" class="py-5 d-flex justify-content-center align-items-center" style="margin: 100 auto">
    <div class="container h-custom">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <h2>Elenco Transponder</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Codice Transponder</th>
                            <th>CF UTENTE</th>
                            <th>ATTIVO</th>
                            <th>PLUS</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%-- Loop attraverso i transponder qui --%>
                        <% for (Transponder t : (List<Transponder>)request.getAttribute("transponders")) { %>
                        <tr data-id = "<%=t.getCodiceTranspPk()%>">
                            <td><%= t.getCodiceTranspPk() %></td>
                            <td><%= t.getCodiceTransponder() %></td>
                            <td><%= (t.getUtente() != null) ? t.getUtente().getCodiceFiscalePk() : "" %></td>
                            <td><%= t.getAttivo() %></td>
                            <td><%= t.getPlus() %></td>
                            <td>
                                <button class="btn btn-danger revoke-btn" data-transponder-id="<%= t.getCodiceTranspPk() %>" data-code="<%=t.getCodiceTransponder()%>">Revoca</button>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <%-- Paginazione qui, se necessario --%>
                <%-- Esempio: Visualizzazione di 5 elementi per pagina --%>
                <%-- Pagina 1 | Pagina 2 | Pagina 3 ... --%>
            </div>
        </div>
    </div>
</section>
<% } %>

<div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title" id="successModalLabel">Rimozione effettuata</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <i class="fas fa-check-circle fa-5x text-success"></i>
                <p class="mt-3" id="modalMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
<!-- Feedback Modal-->
<jsp:include page="feedback_modal.jsp" />
<%--Footer scripts--%>
<jsp:include page="footer_scripts.jsp" />


<script>
    $(document).ready(function() {
        $('.revoke-btn').on('click', function () {

            $.ajax({
                type: 'DELETE',
                url: 'revocaTransponder?transponder_id=' + $(this).data('transponder-id'),
                success: function (response) {
                    $('#successModal').modal('show');
                    $('#modalMessage').text('Il transponder ' + $('tr[data-id="' + response.transponderId + '"] td:nth-child(2)').text() + ' è stato rimosso con successo.');
                    $('tr[data-id="' + response.transponderId + '"]').remove();
                },
                error: function (response) {
                }
            });
        })
    })
</script>

</body>
</html>
