package com.example.supermrcket.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.supermrcket.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {
    TextView   Name_Slid,Number_Slid,Email_Slid,Devolpment_Slid,location,clock_Slid;
    DatabaseReference reference;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Name_Slid=root.findViewById(R.id.Name_Slid);


        clock_Slid=root.findViewById(R.id.et_clock);
        location=root.findViewById(R.id.et_name);
        Number_Slid=root.findViewById(R.id.Number_Slid);
        Devolpment_Slid=root.findViewById(R.id.Devolpment_Slid);
        Email_Slid=root.findViewById(R.id.Email_Slid);
        reference= FirebaseDatabase.getInstance().getReference().child("Data_person");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String clock=dataSnapshot.child("clock").getValue().toString();
                String Map=dataSnapshot.child("Map").getValue().toString();
                String Name=dataSnapshot.child("name").getValue().toString();
                String Email=dataSnapshot.child("Email").getValue().toString();
                String Number=dataSnapshot.child("number").getValue().toString();
                String Devolpment=dataSnapshot.child("Devolpment").getValue().toString();
                clock_Slid.setText(clock);
                Name_Slid.setText(Name);
                Number_Slid.setText(Number);
                Email_Slid.setText(Email);
                Devolpment_Slid.setText(Devolpment);
                location.setText(Map);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });











        return root;
    }
}
