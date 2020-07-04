package com.lilas.telcellandroidtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lilas.telcellandroidtest.R;
import com.lilas.telcellandroidtest.model.PersonInfo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PersonInfoAdapter extends RecyclerView.Adapter<PersonInfoAdapter.MyViewHolder> {
    private List<PersonInfo> personInfoList = new ArrayList<>();
    private LayoutInflater mInflater;


    public PersonInfoAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NotNull
    @Override
    public PersonInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.person_info_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonInfoAdapter.MyViewHolder holder, int position) {
        holder.tvCurrency.setText(personInfoList.get(position).getCurrency());
        holder.tvBalance.setText(String.valueOf(personInfoList.get(position).getBalance()));
    }

    @Override
    public int getItemCount() {
        return personInfoList.size();
    }

    public void setPersonList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCurrency;
        public TextView tvBalance;

        public MyViewHolder(View v) {
            super(v);
            tvCurrency = v.findViewById(R.id.tvCurrency);
            tvBalance = v.findViewById(R.id.tvBalance);
        }

    }
}