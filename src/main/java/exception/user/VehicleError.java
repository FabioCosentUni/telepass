package exception.user;

public enum VehicleError {

    NON_EXISTENT_TYPOLOGY("VP001", "Tipologia veicolo non valida."),
    GENERIC_ERROR("VP002", "Inserimento non andato a buon fine."),
    VEHICLE_ALREADY_REGISTERED("VP003", "Veicolo già associato a un altro utente.");



    private final String errorCode;
    private final String errorMessage;

    VehicleError(String errorCode, String errorMessage) {
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
