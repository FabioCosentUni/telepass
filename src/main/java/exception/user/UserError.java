package exception.user;

public enum UserError {

    INCORRECT_CF("TP001", "Nessun utente associato a questo Codice Fiscale."),
    INCORRECT_PASSWORD("TP002", "Password non valida."),
    USER_ALREADY_REGISTERED("TP003", "Attenzione! Codice Fiscale già associata ad un altro utente."),

    USER_EMAIL_ALREADY_REGISTERED("TP004", "Attenzione! Email già associata ad un altro utente."),

    TRANSPONDER_NOT_AVAILABLE("TP004", "Attenzione! Non ci sono attualmente disponibili transponder."),

    PAYMENT_OPTION_NOT_FOUND("TP005", "Attenzione! Opzione di pagamento non trovata.");

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
