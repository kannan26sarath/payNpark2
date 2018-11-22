package com.example.kanna.paynpark.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanna.paynpark.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        try {

            JSONObject jsonobject = searchData.getJSONObject(i);
            viewHolder.txtid.setText(jsonobject.getString("park_id"));
            viewHolder.txtmob.setText(jsonobject.getString("park_mob"));
            viewHolder.txtcat.setText(jsonobject.getString("park_catgry"));
            viewHolder.txtdate.setText(jsonobject.getString("park_date"));
            viewHolder.txtslote.setText(jsonobject.getString("slote_id"));
            viewHolder.txtvho.setText(jsonobject.getString("park_vehno"));
            if(jsonobject.getString("park_catgry").equals("2 wheeler")){

                viewHolder.imageView.setImageResource(R.drawable.bikelogo);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return searchData.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtid;
        public TextView txtmob;
        public TextView txtcat;
        public TextView txtdate;
        public TextView txtslote;
        public TextView txtvho;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid=itemView.findViewById(R.id.txtsearchparkid);
            txtmob=itemView.findViewById(R.id.txtsearchparkmob);
            txtcat=itemView.findViewById(R.id.txtsearchparkcat);
            txtdate=itemView.findViewById(R.id.txtsearchparkdate);
            txtslote=itemView.findViewById(R.id.txtsearchsloteid);
            txtvho=itemView.findViewById(R.id.txtsearchparkvho);
            imageView=itemView.findViewById(R.id.gifImageView3);


        }
    }
}
