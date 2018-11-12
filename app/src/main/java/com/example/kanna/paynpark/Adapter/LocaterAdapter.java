package com.example.kanna.paynpark.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanna.paynpark.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocaterAdapter extends RecyclerView.Adapter<LocaterAdapter.ViewHolder> {


   static JSONArray Locatedata;

    public LocaterAdapter(JSONArray Locatedata) {
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
        try {
            //JSONObject jsonobject= Locatedata.getJSONArray(i);
            JSONObject jsonobject = Locatedata.getJSONObject(i);
            viewHolder.txtlocate.setText(jsonobject.getString("park_vehno"));
           // viewHolder.txtmob.setText(jsonobject.getString("park_mob"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return Locatedata.length();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtlocate;
        //public TextView txtmob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtlocate=itemView.findViewById(R.id.txtLocateid);
           // txtmob=itemView.findViewById(R.id.txtphno);


        }
    }
}