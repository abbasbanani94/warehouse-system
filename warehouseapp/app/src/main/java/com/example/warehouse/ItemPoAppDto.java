package com.example.warehouse;

public class ItemPoAppDto {

    private Integer id;
    private String itemName;
    private String purchaseOrderNo;
    private String batchNo;
    private String packaging;
    private String recDate;
    private String manDate;
    private String expDate;
    private String countryName;

    public Integer getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public String getPackaging() {
        return packaging;
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
}
