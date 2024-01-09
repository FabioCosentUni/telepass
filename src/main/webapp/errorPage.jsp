<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
</head>
<body>
<div class="container px-5">
    <div class="alert alert-danger mt-5" role="alert">
        <h4 class="alert-heading">Errore!</h4>
        <p>${requestScope.error}</p><%--Inserire codice e messaggio di errore personalizzati--%>
        <hr>
        <p class="mb-0">Contatta l'assistenza per ulteriori informazioni.</p><%--Possibilità di ritornare alla homepage cliccando sul tasto--%>
    </div>
</div>
</body>
</html>
