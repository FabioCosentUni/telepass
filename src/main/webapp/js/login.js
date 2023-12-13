function validateRequest() {
    let cf = $('#cf_login').val();
    let password = $('#password').val();

    if (cf === "") {
        $('#cf_login').addClass('is-invalid');
        $('#invalidEmail').text('Inserire un codice fiscale');
    }

    if (password === "") {
        $('#password').addClass('is-invalid');
        $('#invalidPassword').text('Inserirere una password');
    }

    if (cf === "" || password === "") {
        return false;
    }
}

$(document).ready(function () {
    $('#cf_login').keyup(function () {
        $('#cf_login').removeClass('is-invalid');
        $('#invalidEmail').text('');
    });

    $('#password').keyup(function () {
        $('#password').removeClass('is-invalid');
        $('#invalidPassword').text('');
    });
});