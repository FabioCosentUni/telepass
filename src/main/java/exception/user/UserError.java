package exception.user;

public enum UserError {

    INCORRECT_CF("TP001", "Nessun utente associato a questo Codice Fiscale."),
    INCORRECT_PASSWORD("TP002", "Password non valida."),

    USER_ALREADY_REGISTERED("TP003", "Attenzione! Codice Fiscale già associata ad un altro utente.");

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
