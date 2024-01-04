
function submitForm() {
    document.getElementById("autostradaForm").submit();
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
