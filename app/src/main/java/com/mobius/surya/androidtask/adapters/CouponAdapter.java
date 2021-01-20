package com.mobius.surya.androidtask.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobius.surya.androidtask.CouponDetails;
import com.mobius.surya.androidtask.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Surya Pavan on 19,January,2021
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.MyViewHolder> {
    private List<CouponDetails> couponDetails;
    private Context context;
    List<Double> list_otc = new ArrayList<>();
    List<Double> list_wag = new ArrayList<>();
    List<Double> list_otc_per = new ArrayList<>();
    List<Double> list_wag_per = new ArrayList<>();
    List<Double> list_min_deposit = new ArrayList<>();
    SimpleDateFormat sdf, output;
    String until_date;
    Date date;
    SlabAdapter slabAdapter;

    public CouponAdapter(Context context, List<CouponDetails> couponDetails) {
        this.context = context;
        this.couponDetails = couponDetails;

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        output = new SimpleDateFormat("dd MMM , yyyy hh:mm a ");

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_coupon, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_couponCode.setText(couponDetails.get(position).getCode());
        holder.tv_ribbon_msg.setText(couponDetails.get(position).getRibbon_msg());
        holder.tv_wager_to_release.setText(Html.fromHtml("For every "+ "<font color='yellow'>" +"\u20B9" + couponDetails.get(position).getWager_to_release_ratio_numerator() + "</font> , "+"<font color='yellow'>\u20B9" + couponDetails.get(position).getWager_to_release_ratio_denominator() + "</font>"+ " will be released from the bonus amount"));
        holder.tv_wager_bonus_expiry.setText(Html.fromHtml("Bonus expiry " + "<font color='yellow'>" + couponDetails.get(position).getWager_bonus_expiry() + " days" + "</font>" + " from the issue."));
        list_otc.clear();
        list_wag.clear();
        list_otc_per.clear();
        list_wag_per.clear();
        list_min_deposit.clear();

        for (int i = 0; i < couponDetails.get(position).getSlabsList().size(); i++) {

            list_wag_per.add(couponDetails.get(position).getSlabsList().get(i).getWagered_percent());
            list_otc_per.add(couponDetails.get(position).getSlabsList().get(i).getOtc_percent());
            list_wag.add(couponDetails.get(position).getSlabsList().get(i).getWagered_max());
            list_otc.add(couponDetails.get(position).getSlabsList().get(i).getOtc_max());
            list_min_deposit.add(couponDetails.get(position).getSlabsList().get(i).getMin());
        }

        double max_value_per = Collections.max(list_wag_per);
        double max_otc_per = Collections.max(list_otc_per);
        double max_value = Collections.max(list_wag);
        double max_otc = Collections.max(list_otc);
        double min_deposit = Collections.min(list_min_deposit);

        holder.tv_max_per.setText("Get " + (max_value_per + max_otc_per) + "%" + " upto \u20B9" + (max_value + max_otc));
        holder.tv_couponCode.setText(couponDetails.get(position).getCode());

        try {
            date = sdf.parse(couponDetails.get(position).getValid_until());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        until_date = output.format(date);
        holder.tv_valid_until.setText("valid till " + until_date);
        holder.tv_min_deposit.setText("" + min_deposit);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(mLayoutManager);
        slabAdapter = new SlabAdapter(context, couponDetails.get(position).getSlabsList());
        holder.recyclerView.setAdapter(slabAdapter);
    }

    @Override
    public int getItemCount() {
        return couponDetails.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_couponCode, tv_ribbon_msg, tv_wager_bonus_expiry, tv_wager_to_release,
                tv_max_per, tv_valid_until, tv_min_deposit;
        public RecyclerView recyclerView;

        public MyViewHolder(View v) {
            super(v);
            tv_couponCode = v.findViewById(R.id.coupon_code);
            tv_ribbon_msg = v.findViewById(R.id.ribbon_msg);
            tv_wager_bonus_expiry = v.findViewById(R.id.wager_bonus_expiry);
            tv_wager_to_release = v.findViewById(R.id.wager_to_release);
            tv_max_per = v.findViewById(R.id.max_per);
            tv_valid_until = v.findViewById(R.id.valid_until);
            tv_min_deposit = v.findViewById(R.id.min_deposit);
            recyclerView = v.findViewById(R.id.recyclerView_slab);
        }
    }

}
