package com.example.supermrcket.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.supermrcket.Fruits.Fruits;
import com.example.supermrcket.Milk.milk;
import com.example.supermrcket.Order_Admin;
import com.example.supermrcket.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    ImageSlider sliderView;
    private HomeViewModel homeViewModel;
    ImageView Fruits_Image,Milk;
    FloatingActionButton Order;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);












        Milk=root.findViewById(R.id.Milk);
        Fruits_Image=root.findViewById(R.id.Fruits_Image);

        Order=root.findViewById(R.id.Order);




        sliderView =root.findViewById(R.id.imageSlider);

List<SlideModel>slideModels =new ArrayList<>();
        slideModels.add(new SlideModel("https://p0.pxfuel.com/preview/671/76/62/aisle-background-buy-clean.jpg","احصل على مكونات اكلاتك المفضلة من سوبرماركت  اون لاين بافضل سعر من جوميا مصر"));
        slideModels.add(new SlideModel("https://previews.123rf.com/images/tea/tea1707/tea170700912/82910020-seoul-south-korea-circa-may-2017-inside-a-grocery-store-in-seoul-cu-is-a-convenience-store-franchise.jpg","أقوى العروض وتشكيلة واسعة من المنتجات! أسعار مميزة"));
        slideModels.add(new SlideModel("https://reader016.vdocuments.mx/reader016/slide/20180607/54785c35b4af9fa2108b4c4f/document-0.png?t=1593210116","مرحبا بك في جنة التسوق! ساعد زبائنك ليتسوقوا وكن جاهزا لبعض المتعة!"));
//        slideModels.add(new SlideModel("","Image4"));
        sliderView.setImageList(slideModels,true);



        Fruits_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Fruits.class));
            }
        });

        Milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), milk.class));
            }
        });
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Order_Admin.class));
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });







        return root;
    }
}
