package com.example.supermrcket.Fruits;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermrcket.R;

class RecyclerViewAdapter extends RecyclerView.ViewHolder  {

    ImageView Image_Sengile;
    TextView Textf;
    TextView Text_Sengile_Des;
    View g;




    public RecyclerViewAdapter(@NonNull View itemView) {
        super(itemView);
        Image_Sengile=itemView.findViewById(R.id.Image_Sengile);
        Textf=itemView.findViewById(R.id.Text);
        Text_Sengile_Des=itemView.findViewById(R.id.Text_Sengile_Des);

        g=itemView;


    }}
