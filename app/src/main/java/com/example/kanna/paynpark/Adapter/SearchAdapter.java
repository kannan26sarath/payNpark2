package com.example.kanna.paynpark.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanna.paynpark.R;

import org.json.JSONArray;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    static JSONArray searchData;

    public SearchAdapter(JSONArray searchData ) {
        this.searchData=searchData;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_cardview,null);
        itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        SearchAdapter.ViewHolder viewHolder=new SearchAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return searchData.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtvno;
        public TextView txtmob;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
