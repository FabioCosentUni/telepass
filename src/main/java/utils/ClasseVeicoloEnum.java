package utils;

/**
 * Enumerazione che rappresenta le classi dei veicoli nel sistema Telepass.
 */
public enum ClasseVeicoloEnum {

    CLASSE_A("CLASSE A", "A"),
    CLASSE_B("CLASSE B", "B"),
    CLASSE_3("CLASSE 3", "3"),
    CLASSE_4("CLASSE 4", "4"),
    CLASSE_5("CLASSE 5", "5");

    private final String className;
    private final String classCode;

    /**
     * Costruttore per ClasseVeicoloEnum.
     *
     * @param className  Il nome della classe del veicolo.
     * @param classCode  Il codice associato alla classe del veicolo.
     */
    ClasseVeicoloEnum(String className, String classCode) {
        this.className = className;
        this.classCode = classCode;
    }

    /**
     * Restituisce il nome della classe del veicolo.
     *
     * @return Il nome della classe del veicolo.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Restituisce il codice associato alla classe del veicolo.
     *
     * @return Il codice associato alla classe del veicolo.
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Restituisce un'istanza di ClasseVeicoloEnum corrispondente al nome della classe specificato.
     *
     * @param className Il nome della classe del veicolo da cercare.
     * @return L'enumerazione ClasseVeicoloEnum corrispondente al nome della classe specificato, se presente; altrimenti, restituisce null.
     */
    public static ClasseVeicoloEnum getClasseEnumByName(String className) {
        for (ClasseVeicoloEnum classeVeicoloEnum : ClasseVeicoloEnum.values()) {
            if (classeVeicoloEnum.getClassName().equals(className)) {
                return classeVeicoloEnum;
            }
        }
        return null;
    }
}
