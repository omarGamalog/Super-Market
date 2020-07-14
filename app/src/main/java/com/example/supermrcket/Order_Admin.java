package com.example.supermrcket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.supermrcket.model.User;
import com.example.supermrcket.model.UserAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Order_Admin extends AppCompatActivity {
    public static final String TAG = "MyTag";

private ImageView backOrder;
    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ChildEventListener mChildListener;
    private List<User> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__admin);
        initViews();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");
        backOrder=findViewById(R.id.backUpon);

        mDataList=new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter=new UserAdapter(this,mDataList);
        mRecyclerView.setAdapter(mUserAdapter);
        backOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),Login.class));
                finish();
            }
        });
        mChildListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                User user=dataSnapshot.getValue(User.class);
                user.setUid(dataSnapshot.getKey());

                mDataList.add(user);
                mUserAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                User user=dataSnapshot.getValue(User.class);
                user.setUid(dataSnapshot.getKey());

                mDataList.remove(user);
                mUserAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mRef.addChildEventListener(mChildListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(mChildListener);
    }







    private void initViews() {

        mRecyclerView=findViewById(R.id.user_recyclerview);
    }
}