package utils;

public enum ClasseVeicoloEnum {

    CLASSE_A("CLASSE A","A"),
    CLASSE_B("CLASSE B","B"),
    CLASSE_3("CLASSE 3","3"),
    CLASSE_4("CLASSE 4", "4"),
    CLASSE_5("CLASSE 5", "5");

    private String className;
    private String classCode;

    ClasseVeicoloEnum(String className, String classCode) {
        this.className = className;
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public String getClassCode() {
        return classCode;
    }

    public static ClasseVeicoloEnum getClasseEnumByName(String className) {
        for (ClasseVeicoloEnum classeVeicoloEnum : ClasseVeicoloEnum.values()) {
            if (classeVeicoloEnum.getClassName().equals(className)) {
                return classeVeicoloEnum;
            }
        }
        return null;
    }

}
