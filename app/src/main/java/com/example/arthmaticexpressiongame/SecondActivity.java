package com.example.arthmaticexpressiongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class SecondActivity extends AppCompatActivity {
    Button btn1, btn2;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate( SecondActivity.this, R.layout.pop_up, null);
                Button close ;
                LinearLayout linearLayout = findViewById(R.id.LOc);
                popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,false);
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0,0);
                close = view.findViewById(R.id.close);


                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();

                        btn1.setVisibility(View.VISIBLE);
                        btn2.setVisibility(View.VISIBLE);

                    }
                });

            }
        });
    }
}