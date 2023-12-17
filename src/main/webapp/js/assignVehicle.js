var regExTarga = /^[A-Z]{2}\d{3}[A-Z]{2}$/;
var regExModello = /^[a-zA-Z\s]+$/;
var regExBrand = /^[a-zA-Z\s]+$/;
var regExColore = /^[a-zA-Z\s]+$/;
var regExTipologia = /^[a-zA-Z\s]+$/;

function validateRequest() {
    var targa = $('#targa_assign').val();
    var modello = $('#modello_assign').val();
    var brand = $('#brand_assign').val();
    var colore = $('#colore_assign').val();
    var tipologia = $('#tipologia_assign').val();

    var assignButton = $('#assignButton');

    var isValid =
        targa.trim() !== '' &&
        regExTarga.test(targa) &&
        modello.trim() !== '' &&
        regExModello.test(modello) &&
        brand.trim() !== '' &&
        regExBrand.test(brand) &&
        colore.trim() !== '' &&
        regExColore.test(colore)
        && tipologia.trim() !== ''&&
        regExTipologia.test(tipologia);

    if (isValid) {
        assignButton.removeClass('disabled');
    } else {
        assignButton.addClass('disabled');

        if (!regExTarga.test(targa)) {
            $('#targa_assign').addClass('is-invalid');
            $('#invalidTarga').text('La targa non è valida');
        }

        if (!regExModello.test(modello)) {
            $('#modello_assign').addClass('is-invalid');
            $('#invalidModello').text('Il modello non è valido');
        }

        if (!regExBrand.test(brand)) {
            $('#brand_assign').addClass('is-invalid');
            $('#invalidBrand').text('Il brand non è valido');
        }

        if (!regExTipologia.test(tipologia)) {
            $('#tipologia_assign').addClass('is-invalid');
            $('#invalidTipologia').text('Il brand non è valido');
        }

        if (!regExColore.test(colore)) {
            $('#colore_assign').addClass('is-invalid');
            $('#invalidColore').text('Il colore non è valido');
        }
    }

    return isValid;
}

var formElements = document.querySelectorAll('#formSignup input, #formSignup textarea');
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

    $('#modello_assign').keyup(function () {
        if (regExModello.test($(this).val())) {
            $(this).removeClass('is-invalid');
            $('#invalidModello').text('');
        }
    });

    $('#brand_assign').keyup(function () {
        if (regExBrand.test($(this).val())) {
            $(this).removeClass('is-invalid');
            $('#invalidBrand').text('');
        }
    });

    $('#tipologia_assign').keyup(function () {
        if (regExBrand.test($(this).val())) {
            $(this).removeClass('is-invalid');
            $('#invalidTipologia').text('');
        }
    });

    $('#colore_assign').keyup(function () {
        if (regExColore.test($(this).val())) {
            $(this).removeClass('is-invalid');
            $('#invalidColore').text('');
        }
    });
});
