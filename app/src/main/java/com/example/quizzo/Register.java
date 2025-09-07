package com.example.quizzo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";


    EditText mFullname,Memail,MPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    ProgressBar progressBar;

    String UserId;

    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        mFullname=findViewById(R.id.fullname);
        Memail=findViewById(R.id.Email);
        MPassword=findViewById(R.id.Password);
        mPhone=findViewById(R.id.Phone);
        mRegisterBtn=findViewById(R.id.registerBtn);
        mLoginBtn=findViewById(R.id.createText);
        progressBar=findViewById(R.id.progressbar);
        fauth =FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        if(fauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mLoginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
    });
mRegisterBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final String email = Memail.getText().toString().trim();
        final String password = MPassword.getText().toString().trim();
        final String fullname = mFullname.getText().toString();
        final String phone = mPhone.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Memail.setError("Email is required");
            return;
        }
        if (password.length() < 6) {
            MPassword.setError("Password must be>=6 character");
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            mFullname.setError("Fullname is required");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            mPhone.setError("Phone is required");
        }
        progressBar.setVisibility(View.VISIBLE);
        fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser fuser = fauth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Register.this, "Register succesful", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());

                        }
                    });
                    Toast.makeText(Register.this, "Register succesful", Toast.LENGTH_SHORT).show();
                    UserId = fauth.getCurrentUser().getUid();
                    DocumentReference documentReference = fstore.collection("users").document(UserId);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Fname",fullname);
                    user.put("email",email);
                    user.put("phone",phone);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG,"onsuccess:user profile is created");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"onFailure:" +e.toString());

                        }
                    });
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));


                } else {
                    Toast.makeText(Register.this, "Register failed" + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
});
}
    }
