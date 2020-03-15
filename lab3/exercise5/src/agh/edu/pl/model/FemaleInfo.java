package agh.edu.pl.model;

public class FemaleInfo {

    private Integer bustSize;
    private CupSize cupSize;
    private Integer waist;
    private Integer hips;

    public Integer getBustSize() {
        return bustSize;
    }

    public CupSize getCupSize() {
        return cupSize;
    }

    public Integer getWaist() {
        return waist;
    }

    public Integer getHips() {
        return hips;
    }

    public void setBustSize(Integer bustSize) {
        this.bustSize = bustSize;
    }

    public void setCupSize(CupSize cupSize) {
        this.cupSize = cupSize;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public void setHips(Integer hips) {
        this.hips = hips;
    }

    public enum CupSize {
        A,B,C,D,E
    }

    public CupSize[] getCupSizes() {
        return CupSize.values();
    }
}
