package com.mobius.surya.androidtask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobius.surya.androidtask.adapters.CouponAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Surya Pavan on 19,January,2021
 */
public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    RecyclerView recyclerView;
    private CouponAdapter couponAdapter;
    private List<CouponDetails> couponDetailsList = new ArrayList<>();
    private List<Slabs> slabsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);


        getDetails();

    }


    private void getDetails() {

        if (Utils.isNetworkAvailable(this)) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            pDialog.setMessage("Loading...");
            pDialog.show();


            ApiInterface apiService = retrofit.create(ApiInterface.class);

            Call<ResponseBody> callback = apiService.getDetails();
            callback.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    try {

                        int code = 0;
                        String result = "";

                        if (response.isSuccessful()) {
                            result = response.body().string();
                            code = response.code();
                        }

                        if (code == 200)
                            processRespData(result);
                        //     Toast.makeText(MainActivity.this, "Error in loading files...", Toast.LENGTH_LONG).show();
                        //}

                    } catch (Exception ex) {

                        if (pDialog.isShowing())
                            pDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                }
            });
        } else
            Toast.makeText(MainActivity.this, "Please connect to internet", Toast.LENGTH_LONG).show();

    }

    private void processRespData(String jsonData) throws JSONException {
        slabsList.clear();
        couponDetailsList.clear();
        JSONObject jsonObjSlab = new JSONObject();
        JSONArray jsonSlab;

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            List<Slabs> list = new ArrayList<>();
            list.clear();
            CouponDetails couponDetails = new CouponDetails();
            jsonObject = jsonArray.getJSONObject(i);
            couponDetails.setId(jsonObject.getString("id"));
            couponDetails.setCode(jsonObject.getString("code"));
            couponDetails.setRibbon_msg(jsonObject.getString("ribbon_msg"));
            couponDetails.setValid_until(jsonObject.getString("valid_until"));
            couponDetails.setWager_bonus_expiry(jsonObject.getInt("wager_bonus_expiry"));
            couponDetails.setWager_to_release_ratio_denominator(jsonObject.getInt("wager_to_release_ratio_denominator"));
            couponDetails.setWager_to_release_ratio_numerator(jsonObject.getInt("wager_to_release_ratio_numerator"));

            jsonSlab = new JSONArray(jsonObject.getString("slabs"));
            for (int j = 0; j < jsonSlab.length(); j++) {

                jsonObjSlab = jsonSlab.getJSONObject(j);
                Slabs slabs = new Slabs();

                slabs.setMin(jsonObjSlab.getDouble("min"));
                slabs.setMax(jsonObjSlab.getDouble("max"));
                slabs.setWagered_percent(jsonObjSlab.getDouble("wagered_percent"));
                slabs.setWagered_max(jsonObjSlab.getDouble("wagered_max"));
                slabs.setOtc_percent(jsonObjSlab.getDouble("otc_percent"));
                slabs.setOtc_max(jsonObjSlab.getDouble("otc_max"));
                list.add(slabs);

            }

            couponDetails.setSlabsList(list);
            couponDetailsList.add(couponDetails);

        }


        for (int i=0;i<couponDetailsList.size();i++)

        couponAdapter = new CouponAdapter(this, couponDetailsList);
        recyclerView.setAdapter(couponAdapter);


    }

}