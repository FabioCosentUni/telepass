package dto;

import java.util.Objects;

public class TransponderDTO {
    private String codiceTranspPk;
    private String cfUtenteFk;
    private String metodoPag;
    private int attivo;
    private int plus;

    // Costruttore vuoto
    public TransponderDTO() {
    }

    // Costruttore con parametri
    public TransponderDTO(String codiceTranspPk, String cfUtenteFk, String metodoPag, int attivo, int plus) {
        this.codiceTranspPk = codiceTranspPk;
        this.cfUtenteFk = cfUtenteFk;
        this.metodoPag = metodoPag;
        this.attivo = attivo;
        this.plus = plus;
    }

    // Metodi getter e setter
    public String getCodiceTranspPk() {
        return codiceTranspPk;
    }

    public void setCodiceTranspPk(String codiceTranspPk) {
        this.codiceTranspPk = codiceTranspPk;
    }

    public String getCfUtenteFk() {
        return cfUtenteFk;
    }

    public void setCfUtenteFk(String cfUtenteFk) {
        this.cfUtenteFk = cfUtenteFk;
    }

    public String getMetodoPag() {
        return metodoPag;
    }

    public void setMetodoPag(String metodoPag) {
        this.metodoPag = metodoPag;
    }

    public int getAttivo() {
        return attivo;
    }

    public void setAttivo(int attivo) {
        this.attivo = attivo;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransponderDTO that = (TransponderDTO) o;

        if (attivo != that.attivo) return false;
        if (plus != that.plus) return false;
        if (!Objects.equals(codiceTranspPk, that.codiceTranspPk))
            return false;
        if (!Objects.equals(cfUtenteFk, that.cfUtenteFk)) return false;
        return Objects.equals(metodoPag, that.metodoPag);
    }

    @Override
    public int hashCode() {
        int result = codiceTranspPk != null ? codiceTranspPk.hashCode() : 0;
        result = 31 * result + (cfUtenteFk != null ? cfUtenteFk.hashCode() : 0);
        result = 31 * result + (metodoPag != null ? metodoPag.hashCode() : 0);
        result = 31 * result + attivo;
        result = 31 * result + plus;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransponderDTO{");
        sb.append("codiceTranspPk='").append(codiceTranspPk).append('\'');
        sb.append(", cfUtenteFk='").append(cfUtenteFk).append('\'');
        sb.append(", metodoPag='").append(metodoPag).append('\'');
        sb.append(", attivo=").append(attivo);
        sb.append(", plus=").append(plus);
        sb.append('}');
        return sb.toString();
    }
}
