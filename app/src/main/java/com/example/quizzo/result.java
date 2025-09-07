package com.example.quizzo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class result extends AppCompatActivity {
    TextView tv,tv2,tv3;

    TextView resultText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        tv=(TextView) findViewById(R.id.tvres);
        tv2=(TextView) findViewById(R.id.tvres2);
        tv3=(TextView) findViewById(R.id.tvres3);

    resultText=findViewById(R.id.resulttext);

    





    }

}