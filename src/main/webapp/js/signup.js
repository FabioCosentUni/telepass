var regExCF = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
var regExEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

function validateRequest() {
    var cf = $('#cf_signup').val();
    var name = $('#name_signup').val();
    var surname = $('#surname_signup').val();
    var address = $('#address_signup').val();
    var city = $('#city_signup').val();
    var region = $('#region_signup').val();
    var email = $('#email_signup').val();
    var password = $('#password').val();

    var registerButton = $('#registerButton').val();

    var isValid =
        cf.trim() !== '' &&
        cf.length === 16 &&
        regExCF.test(cf) &&
        name.trim() !== '' &&
        email.trim() !== '' &&
        regExEmail.test(email) &&
        surname.trim() !== '' &&
        address.trim() !== '' &&
        city.trim() !== '' &&
        region.trim() !== '' &&
        password.trim() !== '';

    if (isValid) {
        $('#registerButton').removeClass('disabled');
    } else {
        $('#registerButton').addClass('disabled');
        if(cf.length !== 16) {
            $('#cf_signup').addClass('is-invalid');
            $('#invalidCF').text('Il codice fiscale deve essere lungo 16 caratteri');
        } else if(!regExCF.test(cf)) {
            $('#cf_signup').addClass('is-invalid');
            $('#invalidCF').text('Il codice fiscale non è valido');
        }

        if(!regExEmail.test(email)) {
            $('#email_signup').addClass('is-invalid');
            $('#invalidEmail').text('L\'email non è valida');
        }
    }

    return isValid;
}

var formElements = document.querySelectorAll('#formSignup input, #formSignup textarea');
formElements.forEach(function (element) {
    element.addEventListener('input', validateRequest);
});

$(document).ready(function () {
    $('#cf_signup').keyup(function () {
        if($(this).val().length === 16 && regExCF.test($(this).val()) ) {
            $('#cf_signup').removeClass('is-invalid');
            $('#invalidCf').text('');
        }
    });

    $('#email_signup').keyup(function () {
        if(regExEmail.test($(this).val())) {
            $('#email_signup').removeClass('is-invalid');
            $('#invalidEmail').text('');
        }
    });
});
