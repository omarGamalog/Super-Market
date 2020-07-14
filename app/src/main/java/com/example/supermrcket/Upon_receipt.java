package com.example.supermrcket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.supermrcket.Fruits.details;
import com.example.supermrcket.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Upon_receipt extends AppCompatActivity {
    public static final String TAG = "MyTag";
    AlertDialog.Builder A;
    private Button mSubmitButton;
    private EditText mInputText, mInputNum, mInputAdde, mInputProdect;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upon_receipt);

        initViews();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");
        this.mSubmitButton.setOnClickListener(this::runCode);

    }

    private void runCode(View view) {
        //insert data here
        final String Adders = mInputAdde.getText().toString();
        final String Prodect = mInputProdect.getText().toString();
        final String name = mInputText.getText().toString();
        final String age = mInputNum.getText().toString();


        if (TextUtils.isEmpty(Adders) || TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(Prodect)) {
            Toast.makeText(this, "All places must be filled", Toast.LENGTH_SHORT).show();

        } else {
            mSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    A = new AlertDialog.Builder(Upon_receipt.this);
                    A.setTitle("notice");
                    A.setMessage(" You are sure that you will send a Order");
                    A.setCancelable(false);
                    A.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Upon_receipt.this, "Data inserted...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(),Send_order.class));
                            User user = new User(name, age, Adders, Prodect);
                            String key = mRef.push().getKey();
                            mRef.child(key).setValue(user);
                        }
                    });
                    A.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog D = A.create();
                    D.show();
                }
            });
            return;
        }
    }

    private void initViews() {
        this.mSubmitButton = findViewById(R.id.btn_run_code);
        this.mInputText = findViewById(R.id.et_name);
        this.mInputNum = findViewById(R.id.et_num);
        this.mInputAdde = findViewById(R.id.et_Adderss);
        this.mInputProdect = findViewById(R.id.et_Name_Prodect);

    }

}