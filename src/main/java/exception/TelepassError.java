package exception;

public enum TelepassError {

    INCORRECT_CF("TP001", "Nessun utente associato a questo Codice Fiscale."),
    INCORRECT_PASSWORD("TP002", "Password non valida."),
    USER_ALREADY_REGISTERED("TP003", "Attenzione! Codice Fiscale già associata ad un altro utente."),

    USER_EMAIL_ALREADY_REGISTERED("TP004", "Attenzione! Email già associata ad un altro utente."),

    TRANSPONDER_NOT_AVAILABLE("TP005", "Attenzione! Non ci sono attualmente transponder disponibili."),

    CARD_EXPIRED("TP006", "Attenzione! Carta scaduta. Inserire una carta valida"),

    PAYMENT_OPTION_NOT_FOUND("TP007", "Attenzione! Opzione di pagamento non trovata."),

    METHOD_PAYMENT_DIFFERENT("TP014", "Attenzione! Numero di carta già presente. Le informazioni del metodo di pagamento sono diverse da quelle presenti nel sistema."),

    VIEW_NOT_PERMITTED("TP008", "Attenzione! Non hai i permessi per visualizzare questa pagina."),

    NON_EXISTENT_TYPOLOGY("TP009", "Tipologia veicolo non valida."),
    VEHICLE_ALREADY_REGISTERED("TP011", "Veicolo già associato a un altro utente."),

    TELEPASS_ALREADY_REGISTERED("TP012", "Telepass già presente nel sistema."),

    TELEPASS_NOT_FOUND("TP013", "Telepass non trovato."),

    TRANSPONDER_REVOKED("TP014","Attenzione. Non risulta associato nessun transponder. Contattare l'help desk per maggiori dettagli."),

    GENERIC_ERROR("TP999", "Inserimento non andato a buon fine.");

    private final String errorCode;
    private final String errorMessage;

    TelepassError(String errorCode, String errorMessage) {
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
