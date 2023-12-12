function validateRequest() {
    var name = $('#name_signup').val();
    var surname = $('#surname_signup').val();
    var address = $('#address_signup').val();
    var city = $('#city_signup').val();
    var region = $('#region_signup').val();
    var email = $('#email_signup').val();
    var password = $('#password').val();

    var registerButton = $('#registerButton').val();

    var isValid =
        name.trim() !== '' &&
        surname.trim() !== '' &&
        address.trim() !== '' &&
        city.trim() !== '' &&
        region.trim() !== '' &&
        email.trim() !== '' &&
        password.trim() !== '';

    if (isValid) {
        $('#registerButton').removeClass('disabled');
    } else {
        $('#registerButton').addClass('disabled');
    }

    return isValid;
}

var formElements = document.querySelectorAll('#formSignup input, #formSignup textarea');
formElements.forEach(function (element) {
    element.addEventListener('input', validateRequest);
});