package com.example.warehouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemPoClient {

    @GET("/itemPo/app/details/{id}")
    Call<ItemPoAppDto> findItemPoDetailsForApp (@Path("id") Integer id);

    @GET("/itemPo/app/inventory/{id}")
    Call<List<InventoryAppDto>> findItemPoInventoryForApp (@Path("id") Integer id);
}
