package com.mobius.surya.androidtask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Surya Pavan on 19,January,2021
 */
public interface ApiInterface {

    @GET("v3/4c663239-03af-49b5-bcb3-0b0c41565bd2")
    Call<ResponseBody> getDetails();

}
