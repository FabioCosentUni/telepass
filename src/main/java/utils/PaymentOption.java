package utils;

/**
 * Enumerazione che rappresenta le opzioni di pagamento nel sistema Telepass.
 */
public enum PaymentOption {

    CARTA_DI_CREDITO(0, "Carta di credito"),
    BANCOMAT(1, "Bancomat");

    private final int id;
    private final String name;

    /**
     * Costruttore per PaymentOption.
     *
     * @param id   L'ID associato all'opzione di pagamento.
     * @param name Il nome dell'opzione di pagamento.
     */
    PaymentOption(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Restituisce l'ID associato all'opzione di pagamento.
     *
     * @return L'ID dell'opzione di pagamento.
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il nome dell'opzione di pagamento.
     *
     * @return Il nome dell'opzione di pagamento.
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce un'istanza di PaymentOption corrispondente all'ID specificato.
     *
     * @param id L'ID dell'opzione di pagamento da cercare.
     * @return L'enumerazione PaymentOption corrispondente all'ID specificato, se presente; altrimenti, restituisce null.
     */
    public static PaymentOption getPaymentById(int id) {
        for (PaymentOption payment : PaymentOption.values()) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }
}
