package utils;

public enum PaymentOption {

    CARTA_DI_CREDITO(0, "Carta di credito"),

    BANCOMAT(1, "Bancomat");

    private int id;
    private String name;

    PaymentOption(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static PaymentOption getPaymentById(int id) {
        for (PaymentOption payment : PaymentOption.values()) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }
}
