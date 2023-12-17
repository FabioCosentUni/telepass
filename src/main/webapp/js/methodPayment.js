var regExNome = /^[a-zA-Z\s]+$/;
var regExCognome = /^[a-zA-Z\s]+$/;
var regExNumeroCarta = /^\d{16}$/;
var regExScadenza = /^\d{4}-\d{2}-\d{2}$/;
var regExCvc = /^\d{3}$/;

function validateRequest() {
    var nome = $('#nome_assign').val();
    var cognome = $('#cognome_assign').val();
    var numeroCarta = $('#numero_carta_assign').val();
    var scadenza = $('#scadenza_assign').val();
    var cvc = $('#cvc_assign').val();

    var assignButton = $('#confirmButton');

    var isValid =
        nome.trim() !== '' &&
        regExNome.test(nome) &&
        cognome.trim() !== '' &&
        regExCognome.test(cognome) &&
        numeroCarta.trim() !== '' &&
        regExNumeroCarta.test(numeroCarta) &&
        scadenza.trim() !== '' &&
        regExScadenza.test(scadenza) &&
        cvc.trim() !== '' &&
        regExCvc.test(cvc);

    if (isValid) {
        assignButton.removeClass('disabled');
    } else {
        assignButton.addClass('disabled');

        if (!regExNome.test(nome)) {
            $('#nome_assign').addClass('is-invalid');
            $('#invalidNomePrp').text('Il nome non è valido');
        }

        if (!regExCognome.test(cognome)) {
            $('#cognome_assign').addClass('is-invalid');
            $('#invalidCognome').text('Il cognome non è valido');
        }

        if (!regExNumeroCarta.test(numeroCarta)) {
            $('#numero_carta_assign').addClass('is-invalid');
            $('#invalidNumeroCarta').text('Il numero carta non è valido');
        }

        if (!regExScadenza.test(scadenza)) {
            $('#scadenza_assign').addClass('is-invalid');
            $('#invalidScadenza').text('La scadenza non è valida');
        }

        if (!regExCvc.test(cvc)) {
            $('#cvc_assign').addClass('is-invalid');
            $('#invalidCvc').text('Il CVC non è valido');
        }
    }

    return isValid;
}

var formElements = document.querySelectorAll('#formSignup input');
formElements.forEach(function (element) {
    element.addEventListener('input', validateRequest);
});

$(document).ready(function () {
    $('#nome_assign, #cognome_assign, #numero_carta_assign, #scadenza_assign, #cvc_assign').keyup(function () {
        var fieldName = $(this).attr('id');
        var fieldValue = $(this).val();
        var regEx;

        switch (fieldName) {
            case 'nome_assign':
                regEx = regExNome;
                break;
            case 'cognome_assign':
                regEx = regExCognome;
                break;
            case 'numero_carta_assign':
                regEx = regExNumeroCarta;
                break;
            case 'scadenza_assign':
                regEx = regExScadenza;
                break;
            case 'cvc_assign':
                regEx = regExCvc;
                break;
            default:
                regEx = /^[a-zA-Z\s]+$/; // Default regex for other fields
        }

        if (regEx.test(fieldValue)) {
            $(this).removeClass('is-invalid');
            $('#' + 'invalid' + fieldName.charAt(0).toUpperCase() + fieldName.slice(1)).text('');
        }
    });
});
