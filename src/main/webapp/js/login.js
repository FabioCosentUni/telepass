function validateRequest() {
    let email = $('#email_login').val();
    let password = $('#password').val();

    if (email === "") {
        $('#email_login').addClass('is-invalid');
        $('#invalidEmail').text('Email is required');
    }

    if (password === "") {
        $('#password').addClass('is-invalid');
        $('#invalidPassword').text('Password is required');
    }

    if (email === "" || password === "") {
        return false;
    }
}

$(document).ready(function () {
    $('#email_login').keyup(function () {
        $('#email_login').removeClass('is-invalid');
        $('#invalidEmail').text('');
    });

    $('#password').keyup(function () {
        $('#password').removeClass('is-invalid');
        $('#invalidPassword').text('');
    });
});