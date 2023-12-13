var regEx = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;

function validateRequest() {
    var cf = $('#cf_signup').val();
    var name = $('#name_signup').val();
    var surname = $('#surname_signup').val();
    var address = $('#address_signup').val();
    var city = $('#city_signup').val();
    var region = $('#region_signup').val();
    var password = $('#password').val();

    var registerButton = $('#registerButton').val();

    var isValid =
        cf.trim() !== '' &&
        cf.length === 16 &&
        regEx.test(cf) &&
        name.trim() !== '' &&
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
        } else if(!regEx.test(cf)) {
            $('#cf_signup').addClass('is-invalid');
            $('#invalidCF').text('Il codice fiscale non è valido');
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
        if($(this).val().length === 16 && regEx.test($(this).val()) ) {
            $('#cf_signup').removeClass('is-invalid');
            $('#invalidCf').text('');
        }
    });
});
