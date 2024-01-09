
function submitForm() {
    document.getElementById("autostradaForm").submit();
}

function validateRequest() {
    var veicolo = document.getElementById("veicoloSel").value;
    var entrata = document.getElementById("entrataSelect").value;
    var uscita = document.getElementById("uscitaSelect").value;

    if(veicolo === "-1") {
        alert("Selezionare un veicolo");
        return false;
    }
    if(entrata === "-1") {
        alert("Selezionare un'entrata");
        return false;
    }

    if(uscita === "-1") {
        alert("Selezionare un'uscita");
        return false;
    }

    return true;
}

$(document).ready(function () {
    $("#entrataSelect").change(function () {
        $("#uscitaSelect").attr("disabled", false);

        //abilita tutte le opzioni
        $("#uscitaSelect option").attr("disabled", false);

        //disabilita l'opzione selezionata
        $("#uscitaSelect option[value='" + $(this).val() + "']").attr("disabled", true);
    });
});
