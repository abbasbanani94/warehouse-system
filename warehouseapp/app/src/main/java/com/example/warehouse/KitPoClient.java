package com.example.warehouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface KitPoClient {

    @GET("/kitPo/app/details/{id}")
    Call<KitPoAppDto> findKitPoDetailsForApp (@Path("id") Integer id);

    @GET("/kitPo/app/inventory/{id}")
    Call<List<InventoryAppDto>> findKitPoInventoryForApp (@Path("id") Integer id);
}
