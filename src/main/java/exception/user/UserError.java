package exception.user;

public enum UserError {

    INCORRECT_EMAIL("TP001", "Nessun utente associato a questa email."),
    INCORRECT_PASSWORD("TP002", "Password non valida.");

    private final String errorCode;
    private final String errorMessage;

    UserError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


}
