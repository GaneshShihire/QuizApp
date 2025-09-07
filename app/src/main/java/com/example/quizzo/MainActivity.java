package com.example.quizzo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find ImageView by ID and set OnClickListener for C
        ImageView cImageView = findViewById(R.id.imageview);


        // Find ImageView by ID and set OnClickListener for C++
        ImageView cPlusImageView = findViewById(R.id.imageview3);
        cPlusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CQues(v);
            }
        });

        // Find ImageView by ID and set OnClickListener for C#
        ImageView cSharpImageView = findViewById(R.id.imageView8);
        cSharpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C(v);
            }
        });

        // Find ImageView by ID and set OnClickListener for HTML
        ImageView htmlImageView = findViewById(R.id.imageView9);
        htmlImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTMLQ(v);
            }
        });

        // Find ImageView by ID and set OnClickListener for Java
        ImageView javaImageView = findViewById(R.id.imageView);
        javaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Java(v);
            }
        });
    }

    // Method to handle the click event for C
    public void CQ(View view) {
        Intent i=new Intent(MainActivity.this,c_language.class);
        startActivity(i);
    }

    // Method to handle the click event for C++
    public void CQues(View view) {
//        Intent i=new Intent(MainActivity.this,.class);
//        startActivity(i);

    }

    // Method to handle the click event for C#
    public void C(View view) {
        Intent i=new Intent(MainActivity.this,cs.class);
        startActivity(i);
    }

    // Method to handle the click event for HTML
    public void HTMLQ(View view) {
        Intent i=new Intent(MainActivity.this,HTML.class);
        startActivity(i);
    }

    // Method to handle the click event for Java
    public void Java(View view) {
        Intent i=new Intent(MainActivity.this,JavaQ.class);
        startActivity(i);
    }


}
