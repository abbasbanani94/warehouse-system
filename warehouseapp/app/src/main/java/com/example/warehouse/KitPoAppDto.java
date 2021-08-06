package com.example.warehouse;

public class KitPoAppDto {

    private Integer id;
    private String kitName;
    private String orderNo;
    private String batchNo;
    private String recDate;
    private String manDate;
    private String expDate;
    private String countryName;
    private String location;
    private Integer boxesPerPallet;
    private Integer kitsPerPallet;

    public Integer getId() {
        return id;
    }

    public String getKitName() {
        return kitName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public String getRecDate() {
        return recDate;
    }

    public String getManDate() {
        return manDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getLocation() {
        return location;
    }

    public Integer getBoxesPerPallet() {
        return boxesPerPallet;
    }

    public Integer getKitsPerPallet() {
        return kitsPerPallet;
    }
}
