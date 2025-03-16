package com.office.notfound.samusil.model.dto;

public class OfficeDTO {

    private int officeCode;
    private int storeCode;
    private String officeType;
    private int officeNum;
    private int officePrice;
    private String officeThumbnail;

    public OfficeDTO() {
    }

    public OfficeDTO(int officeCode, int storeCode, String officeType, int officeNum, int officePrice, String officeThumbnail) {
        this.officeCode = officeCode;
        this.storeCode = storeCode;
        this.officeType = officeType;
        this.officeNum = officeNum;
        this.officePrice = officePrice;
        this.officeThumbnail = officeThumbnail;
    }

    public int getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(int officeCode) {
        this.officeCode = officeCode;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public int getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(int officeNum) {
        this.officeNum = officeNum;
    }

    public int getOfficePrice() {
        return officePrice;
    }

    public void setOfficePrice(int officePrice) {
        this.officePrice = officePrice;
    }

    public String getOfficeThumbnail() {
        return officeThumbnail;
    }

    public void setOfficeThumbnail(String officeThumbnail) {
        this.officeThumbnail = officeThumbnail;
    }

    @Override
    public String toString() {
        return "OfficeDTO{" +
                "officeCode=" + officeCode +
                ", storeCode=" + storeCode +
                ", officeType='" + officeType + '\'' +
                ", officeNum=" + officeNum +
                ", officePrice=" + officePrice +
                ", officeThumbnail='" + officeThumbnail + '\'' +
                '}';
    }
}
