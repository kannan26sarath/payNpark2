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
            viewHolder.txtLocateid.setText(jsonobject.getString("park_id"));
            viewHolder.txtLocateVho.setText(jsonobject.getString("park_vehno"));
            viewHolder.txtLocateCat.setText(jsonobject.getString("park_catgry"));
            viewHolder.txtLocateDate.setText(jsonobject.getString("park_date"));
            viewHolder.txtLocateMob.setText(jsonobject.getString("park_mob"));
            viewHolder.txtLocateSloteId.setText(jsonobject.getString("slote_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return Locatedata.length();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtLocateid;
        public TextView txtLocateVho;
        public TextView txtLocateCat;
        public TextView txtLocateDate;
        public TextView txtLocateMob;
        public TextView txtLocateSloteId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLocateid=itemView.findViewById(R.id.txtLocateid);
            txtLocateVho=itemView.findViewById(R.id.txtLocatevho);
            txtLocateCat=itemView.findViewById(R.id.txtLocatecat);
            txtLocateDate=itemView.findViewById(R.id.txtLocateDate);
            txtLocateMob=itemView.findViewById(R.id.txtLocatemob);
            txtLocateSloteId=itemView.findViewById(R.id.txtLocateSloteid);

        }
    }
}