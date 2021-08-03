package com.example.warehouse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemPoClient {

    @GET("/itemPo/app/{id}")
    Call<ItemPoAppDto> findItemPoDetailsForApp (@Path("id") Integer id);
}
