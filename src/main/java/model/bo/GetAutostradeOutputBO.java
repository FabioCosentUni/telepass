package model.bo;

import model.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAutostradeOutputBO implements BusinessObject {

    private List<String> autostrade = new ArrayList<>();

    public List<String> getAutostrade() {
        return autostrade;
    }

    public void setAutostrade(List<String> autostrade) {
        this.autostrade = autostrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetAutostradeOutputBO that = (GetAutostradeOutputBO) o;

        return Objects.equals(autostrade, that.autostrade);
    }

    @Override
    public int hashCode() {
        return autostrade != null ? autostrade.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAutostradeOutputBO{");
        sb.append("autostrade=").append(autostrade);
        sb.append('}');
        return sb.toString();
    }
}
