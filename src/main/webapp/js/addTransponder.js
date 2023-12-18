function validateRequest() {
    var codiceTransponder = $('#codice_transponder').val();

    var registerButton = $('#registerButton').val();

    var isValid =
        codiceTransponder.trim() !== '';

    if (isValid) {
        $('#registerButton').removeClass('disabled');
    } else {
        $('#registerButton').addClass('disabled');
    }

    return isValid;
}

var formElements = document.querySelectorAll('#formTransponder input, #formTransponder textarea');
formElements.forEach(function (element) {
    element.addEventListener('input', validateRequest);
});
