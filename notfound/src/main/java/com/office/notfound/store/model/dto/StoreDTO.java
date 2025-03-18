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
    private String storeThumbnailUrl;
    private String storeImg1Url;
    private String storeImg2Url;
    private String storeImg3Url;

    public StoreDTO() {
    }

    public StoreDTO(int storeCode,
                    String storeName, String storeCity, String storeGu, String storeAddress,
                    double latitude, double longitude,
                    String description, String storeThumbnailUrl, String storeImg1Url, String storeImg2Url, String storeImg3Url) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.storeCity = storeCity;
        this.storeGu = storeGu;
        this.storeAddress = storeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.storeThumbnailUrl = storeThumbnailUrl;
        this.storeImg1Url = storeImg1Url;
        this.storeImg2Url = storeImg2Url;
        this.storeImg3Url = storeImg3Url;
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

    public String getStoreThumbnailUrl() {
        return storeThumbnailUrl;
    }

    public void setStoreThumbnailUrl(String storeThumbnailUrl) {
        this.storeThumbnailUrl = storeThumbnailUrl;
    }

    public String getStoreImg1Url() {
        return storeImg1Url;
    }

    public void setStoreImg1Url(String storeImg1Url) {
        this.storeImg1Url = storeImg1Url;
    }

    public String getStoreImg2Url() {
        return storeImg2Url;
    }

    public void setStoreImg2Url(String storeImg2Url) {
        this.storeImg2Url = storeImg2Url;
    }

    public String getStoreImg3Url() {
        return storeImg3Url;
    }

    public void setStoreImg3Url(String storeImg3Url) {
        this.storeImg3Url = storeImg3Url;
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
                ", storeThumbnailUrl='" + storeThumbnailUrl + '\'' +
                ", storeImg1Url='" + storeImg1Url + '\'' +
                ", storeImg2Url='" + storeImg2Url + '\'' +
                ", storeImg3Url='" + storeImg3Url + '\'' +
                '}';
    }
}