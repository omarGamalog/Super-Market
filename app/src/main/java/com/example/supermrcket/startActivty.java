package com.example.supermrcket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class startActivty extends AppCompatActivity {
    Button B;
    TextView Forget_Passowrd;
    EditText Email, password;
    FirebaseAuth mfAuth;
    TextView ott;
    Button uuuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activty);
        Forget_Passowrd = findViewById(R.id.Forget_Passowrd);
        Email = findViewById(R.id.email);
        password = findViewById(R.id.et_name);
        ott = findViewById(R.id.NLogin);
        B = findViewById(R.id.button);
        mfAuth = FirebaseAuth.getInstance();



        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is ....");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Email.setError("pass is ....");
                    return;
                }


                mfAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                         @Override
                                                                                         public void onComplete(@NonNull Task<AuthResult> task) {

                                                                                             if (task.isSuccessful()) {
                                                                                                 Toast.makeText(getApplicationContext(), "Login Dona", Toast.LENGTH_SHORT).show();
                                                                                                 startActivity(new Intent(getApplicationContext(), Login.class));
                                                                                                 finish();
                                                                                             } else {
                                                                                                 Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                                                                                             }
                                                                                         }

                                                                                     }
                );
            }


        });


        Forget_Passowrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText reseMail = new EditText(v.getContext());
                AlertDialog.Builder Forget_Passowrd = new AlertDialog.Builder(v.getContext());
                Forget_Passowrd.setTitle("Reset Password");
                Forget_Passowrd.setMessage("Enter Your Email To Received Reset Link.");
                Forget_Passowrd.setView(reseMail);
                Forget_Passowrd.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = reseMail.getText().toString();
                        mfAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Reset Link sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error ! Reset Link is Not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        });
                    }
                });


                Forget_Passowrd.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                Forget_Passowrd.create().show();


            }
        });


    }

}
