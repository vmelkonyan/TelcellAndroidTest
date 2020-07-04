package com.lilas.telcellandroidtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lilas.telcellandroidtest.R;
import com.lilas.telcellandroidtest.model.Person;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.MyViewHolder> {
    private List<Person> mPersonList = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public PersonListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NotNull
    @Override
    public PersonListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.person_item_row, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mFirstName.setText(mPersonList.get(position).getFirstName());
        holder.mLastName.setText(mPersonList.get(position).getLastName());
        holder.mTvInfoCount.setText(String.valueOf(mPersonList.get(position).getPersonInfoList().size()));
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    public void setmPersonList(List<Person> mPersonList) {
        this.mPersonList = mPersonList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mFirstName;
        private TextView mLastName;
        private TextView mTvInfoCount;

        public MyViewHolder(View v) {
            super(v);
            mFirstName = v.findViewById(R.id.tvFirstName);
            mLastName = v.findViewById(R.id.tvLastName);
            mTvInfoCount = v.findViewById(R.id.tvInfoCount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}