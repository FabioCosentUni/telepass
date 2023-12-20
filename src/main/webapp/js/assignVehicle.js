var regExTarga = /^[A-Z]{2}\d{3}[A-Z]{2}$/;

function validateRequest() {
    var targa = $('#targa_assign').val();
    var tipologia = $('#tipologia_assign').find(":selected").val();

    var assignButton = $('#assignButton');

    var isValid =
        targa.trim() !== '' &&
        regExTarga.test(targa) &&
        tipologia !== '-1'

    if (isValid) {
        assignButton.removeClass('disabled');
    } else {
        assignButton.addClass('disabled');

        if (!regExTarga.test(targa)) {
            $('#targa_assign').addClass('is-invalid');
            $('#invalidTarga').text('La targa non è valida');
        }

        if (!regExTipologia.test(tipologia)) {
            $('#tipologia_assign').addClass('is-invalid');
            $('#invalidTipologia').text('Il brand non è valido');
        }
    }

    return isValid;
}

var formElements = document.querySelectorAll('#formSignup input, #formSignup select');
formElements.forEach(function (element) {
    element.addEventListener('input', validateRequest);
});

$(document).ready(function () {
    $('#targa_assign').keyup(function () {
        if (regExTarga.test($(this).val())) {
            $(this).removeClass('is-invalid');
            $('#invalidTarga').text('');
        }
    });
});
