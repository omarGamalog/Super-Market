package com.example.supermrcket.Fruits;


import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.supermrcket.R;
import com.example.supermrcket.Upon_receipt;
import com.example.supermrcket.onlinepayment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class details extends AppCompatActivity {
    private ImageView imageView, backdetils;
    private TextView ImageName, ImageName_Des;
    DatabaseReference reference, Dataref;
    StorageReference Storreference;
    Button Deleta,Buy;
    TextView Texttoolbar;
//    private android.widget.RadioGroup RadioGroup;
//    private android.widget.RadioButton RadioButton;
//    ToggleButton toggleButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Buy=findViewById(R.id.Buy);
        imageView = findViewById(R.id.Image_milk);
        ImageName = findViewById(R.id.ImageName_milk);
        ImageName_Des = findViewById(R.id.ImageName_Des_milk);
        Deleta = findViewById(R.id.Deleta_milk);
        reference = FirebaseDatabase.getInstance().getReference("Fruits");
//        RadioGroup = findViewById(R.id.milk_Re);
        Texttoolbar = findViewById(R.id.Texttoolbar);
        backdetils = findViewById(R.id.backdetils);
        backdetils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Fruits.class));
                finish();
            }
        });


        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Upon_receipt.class));
            }
        });


//        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(android.widget.RadioGroup group, @IdRes int checkedId) {
//
//                RadioButton = findViewById(checkedId);
//
//
//                switch (RadioButton.getId()) {
//                    case R.id.R2: {
//                        startActivity(new Intent(getApplication(), Upon_receipt.class));
//                    }
//                    break;
//                    case R.id.R3: {
//                        startActivity(new Intent(getApplication(), onlinepayment.class));
//
//                    }
//
//
//                }
//            }
//        });


        String CarKey = getIntent().getStringExtra("CarKey");
        Dataref = FirebaseDatabase.getInstance().getReference().child("Fruits").child(CarKey);
        Storreference = FirebaseStorage.getInstance().getReference().child("FruitsImage").child(CarKey + ".jpg");


        reference.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    String carName = dataSnapshot.child("FruitsName").getValue().toString();
                    ImageName.setText(carName);

                    Texttoolbar.setText(carName);
                    String carName_Des = dataSnapshot.child("FruitsPres").getValue().toString();
                    ImageName_Des.setText("ج" + carName_Des);


                    String ImageUrl = dataSnapshot.child("FruitsImage").getValue().toString();
                    Picasso.get().load(ImageUrl).into(imageView);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Deleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dataref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Storreference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplication(), Fruits.class));
                                finish();

                            }
                        });
                    }
                });
            }
        });


    }
}