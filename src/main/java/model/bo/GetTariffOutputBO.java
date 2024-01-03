package model.bo;

import model.BusinessObject;

import java.math.BigDecimal;

public class GetTariffOutputBO implements BusinessObject {

    private BigDecimal tariff;

    public GetTariffOutputBO() {
    }

    public GetTariffOutputBO(BigDecimal tariff) {
        this.tariff = tariff;
    }

    public BigDecimal getTariff() {
        return tariff;
    }

    public void setTariff(BigDecimal tariff) {
        this.tariff = tariff;
    }
}
