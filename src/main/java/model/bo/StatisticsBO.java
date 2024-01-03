package model.bo;

import model.BusinessObject;

public class StatisticsBO implements BusinessObject {

    private double entryPercentage;

    private double exitPercentage;

    public StatisticsBO(double entryPercentage, double exitPercentage) {
        this.entryPercentage = entryPercentage;
        this.exitPercentage = exitPercentage;
    }

    public StatisticsBO() {
    }

    public double getEntryPercentage() {
        return entryPercentage;
    }

    public void setEntryPercentage(double entryPercentage) {
        this.entryPercentage = entryPercentage;
    }

    public double getExitPercentage() {
        return exitPercentage;
    }

    public void setExitPercentage(double exitPercentage) {
        this.exitPercentage = exitPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticsBO that = (StatisticsBO) o;

        if (Double.compare(that.entryPercentage, entryPercentage) != 0) return false;
        return Double.compare(that.exitPercentage, exitPercentage) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(entryPercentage);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(exitPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatisticsBO{");
        sb.append("entryPercentage=").append(entryPercentage);
        sb.append(", exitPercentage=").append(exitPercentage);
        sb.append('}');
        return sb.toString();
    }
}
