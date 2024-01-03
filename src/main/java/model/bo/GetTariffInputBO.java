package model.bo;

import model.BusinessObject;

import java.util.Objects;

public class GetTariffInputBO implements BusinessObject {

    private final String autostrada;
    private final String categoria;

    public GetTariffInputBO(String autostrada, String categoria) {
        this.autostrada = autostrada;
        this.categoria = categoria;
    }

    public String getAutostrada() {
        return autostrada;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetTariffInputBO that = (GetTariffInputBO) o;

        if (!Objects.equals(autostrada, that.autostrada)) return false;
        return Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        int result = autostrada != null ? autostrada.hashCode() : 0;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetTariffInputBO{");
        sb.append("autostrada='").append(autostrada).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
