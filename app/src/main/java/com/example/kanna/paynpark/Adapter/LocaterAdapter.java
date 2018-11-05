package com.example.kanna.paynpark.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanna.paynpark.R;

public class LocaterAdapter extends RecyclerView.Adapter<LocaterAdapter.ViewHolder> {


   static String[] Locatedata;

    public LocaterAdapter(String[] Locatedata) {
        this.Locatedata=Locatedata;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.locatecardview,null);
        itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        ViewHolder viewHolder=new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtvno.setText(Locatedata[i]);

    }

    @Override
    public int getItemCount() {
        return Locatedata.length-1;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtvno;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvno=itemView.findViewById(R.id.txtvno);


        }
    }
}