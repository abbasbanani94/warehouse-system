package com.example.warehouse;


public class ItemInventoryAppDto {

    private String note;
    private String date;
    private String inQty;
    private String outQty;
    private String stock;

    public ItemInventoryAppDto(String note, String date, String inQty, String outQty, String stock) {
        this.note = note;
        this.date = date;
        this.inQty = inQty;
        this.outQty = outQty;
        this.stock = stock;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public String getInQty() {
        return inQty;
    }

    public String getOutQty() {
        return outQty;
    }

    public String getStock() {
        return stock;
    }
}
