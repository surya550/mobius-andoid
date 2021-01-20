package com.mobius.surya.androidtask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mobius.surya.androidtask.R;
import com.mobius.surya.androidtask.Slabs;

import java.util.List;

/**
 * Created by Surya Pavan on 19,January,2021
 */
public class SlabAdapter extends RecyclerView.Adapter<SlabAdapter.MyViewHolder> {
    private List<Slabs> slabsList;
    private Context context;

    public SlabAdapter(Context context, List<Slabs> slabsList) {
        this.context = context;
        this.slabsList = slabsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_slab, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            if (position == 0)
                holder.tv_purchase_amount.setText("< " + (slabsList.get(position).getMax() + 1));
            else if (position == 1)
                holder.tv_purchase_amount.setText(">=" + slabsList.get(position).getMin() + " & < " + (slabsList.get(position).getMax()));
            else if ( position == 2)
                holder.tv_purchase_amount.setText(">= " + (slabsList.get(position).getMax()));

        holder.tv_bonus_amount.setText("" + slabsList.get(position).getWagered_percent() + "% (Max. \u20B9" + slabsList.get(position).getWagered_max() + ")");
        holder.tv_instant_cash.setText("" + slabsList.get(position).getOtc_percent() + "% (Max. \u20B9" + slabsList.get(position).getOtc_max() + ")");

    }

    @Override
    public int getItemCount() {
        return slabsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_purchase_amount, tv_bonus_amount, tv_instant_cash;

        public MyViewHolder(View v) {
            super(v);
            tv_purchase_amount = v.findViewById(R.id.purchase_amount);
            tv_bonus_amount = v.findViewById(R.id.bonus_amount);
            tv_instant_cash = v.findViewById(R.id.instant_cash);

        }
    }
}

