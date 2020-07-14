 package com.example.supermrcket.Fruits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.supermrcket.Login;
import com.example.supermrcket.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Fruits extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseRecyclerOptions<ImageUploadInfo> options;
    FirebaseRecyclerAdapter<ImageUploadInfo, RecyclerViewAdapter> adapter ;
    DatabaseReference Data;
ImageView backfruits;
    List<String> movesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        Data= FirebaseDatabase.getInstance().getReference().child("Fruits");
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);

        movesList=new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        backfruits=findViewById(R.id.backfruits);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        recyclerView.setHasFixedSize(true);
        backfruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Login.class));
                finish();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        LoadData();
    }
    private void LoadData() {

        options=new FirebaseRecyclerOptions.Builder<ImageUploadInfo>().setQuery(Data,ImageUploadInfo.class).build();

        adapter=new FirebaseRecyclerAdapter<ImageUploadInfo, RecyclerViewAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerViewAdapter holder, final  int Position, @NonNull ImageUploadInfo model) {





                holder.Text_Sengile_Des.setText(model.getImageDes());
                holder.Textf.setText(model.getImageName());
//                Picasso.get().load(model.getImageURL()).into(holder.Image_Sengile);

                holder.g.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplication(), details.class);
                        intent.putExtra("CarKey",getRef(Position).getKey());
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.res,parent,false);
                return new RecyclerViewAdapter(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }}
