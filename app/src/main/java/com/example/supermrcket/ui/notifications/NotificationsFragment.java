package com.example.supermrcket.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.supermrcket.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class NotificationsFragment extends Fragment {
    private ImageView ImageViewAdd;
    private EditText InputImageName,Pres;
    private TextView TextViewProgres;
    android.widget.ProgressBar ProgressBar;
    private Button  btnUplode;
    private FloatingActionButton ChooseButton ;
    int  Image_Request_Code = 7;
    Uri ImagUrl;
    boolean isImage = false;
    DatabaseReference Dataref;
    StorageReference StorageRef;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);




//        ChooseButton = (FloatingActionButton) root.findViewById(R.id.ButtonChooseImage);
        btnUplode = (Button) root.findViewById(R.id.ButtonUploadImage);
        InputImageName = (EditText) root.findViewById(R.id.ImageNameEditText);
        Pres = (EditText) root.findViewById(R.id.Pres);
        ImageViewAdd = (ImageView) root.findViewById(R.id.ShowImageView);
        ProgressBar = (android.widget.ProgressBar) root.findViewById(R.id.ProgressBar);
        TextViewProgres=(TextView) root.findViewById(R.id.conter);
        TextViewProgres.setVisibility(View.GONE);
        ProgressBar.setVisibility(View.GONE);



        Dataref = FirebaseDatabase.getInstance().getReference().child("Fruits");
        StorageRef = FirebaseStorage.getInstance().getReference("FruitsImage");


        ImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,Image_Request_Code);


            }
        });

        btnUplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ImageName=InputImageName.getText().toString();
                final String pres=Pres.getText().toString();

                if (isImage !=false&&ImageName!=null&&pres!=null){
                    UplodeImage(ImageName,pres);
                    ImageViewAdd.setImageURI(ImagUrl);

                }else{
                    Toast.makeText(getActivity(), "All places must be filled", Toast.LENGTH_SHORT).show();

                }

            }
        });
        return root;
    }













    private void UplodeImage(final String ImageName, final String pres) {


//        Toast.makeText(NotificationsFragment.this, "Ok", Toast.LENGTH_SHORT).show();
        TextViewProgres.setVisibility(View.VISIBLE);
        ProgressBar.setVisibility(View.VISIBLE);


        final String Key = Dataref.push().getKey();
        StorageRef.child(Key + ".jpg").putFile(ImagUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageRef.child(Key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("FruitsName", ImageName);
                        hashMap.put("FruitsPres", pres);
                        hashMap.put("FruitsImage", uri.toString());

                        Dataref.child(Key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Data Uplode Secucfile", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double Por = (taskSnapshot.getBytesTransferred() * 100) / taskSnapshot.getTotalByteCount();
                ProgressBar.setProgress((int) Por);
                TextViewProgres.setText(Por + "%");

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && data != null) {

            ImagUrl = data.getData();
            isImage = true;
            ImageViewAdd.setImageURI(ImagUrl);
        }


    }
}



