package com.office.notfound.store.model.dto;

public class StoreDTO {

    private int storeCode;
    private String storeName;
    private String storeCity;
    private String storeGu;
    private String storeAddress;
    private double latitude;
    private double longitude;
    private String description;
    private String storeThumbnail;
    private String storeImg1;
    private String storeImg2;
    private String storeImg3;

    public StoreDTO() {
    }

    public StoreDTO(int storeCode, String storeName, String storeCity, String storeGu, String storeAddress, double latitude, double longitude, String description, String storeThumbnail, String storeImg1, String storeImg2, String storeImg3) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.storeCity = storeCity;
        this.storeGu = storeGu;
        this.storeAddress = storeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.storeThumbnail = storeThumbnail;
        this.storeImg1 = storeImg1;
        this.storeImg2 = storeImg2;
        this.storeImg3 = storeImg3;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreGu() {
        return storeGu;
    }

    public void setStoreGu(String storeGu) {
        this.storeGu = storeGu;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreThumbnail() {
        return storeThumbnail;
    }

    public void setStoreThumbnail(String storeThumbnail) {
        this.storeThumbnail = storeThumbnail;
    }

    public String getStoreImg1() {
        return storeImg1;
    }

    public void setStoreImg1(String storeImg1) {
        this.storeImg1 = storeImg1;
    }

    public String getStoreImg2() {
        return storeImg2;
    }

    public void setStoreImg2(String storeImg2) {
        this.storeImg2 = storeImg2;
    }

    public String getStoreImg3() {
        return storeImg3;
    }

    public void setStoreImg3(String storeImg3) {
        this.storeImg3 = storeImg3;
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
                "storeCode=" + storeCode +
                ", storeName='" + storeName + '\'' +
                ", storeCity='" + storeCity + '\'' +
                ", storeGu='" + storeGu + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                ", storeThumbnail='" + storeThumbnail + '\'' +
                ", storeImg1='" + storeImg1 + '\'' +
                ", storeImg2='" + storeImg2 + '\'' +
                ", storeImg3='" + storeImg3 + '\'' +
                '}';
    }
}