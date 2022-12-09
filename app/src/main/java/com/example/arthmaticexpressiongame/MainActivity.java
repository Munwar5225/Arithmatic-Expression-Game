package com.example.arthmaticexpressiongame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthmaticexpressiongame.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    float result1 , result2;
    int right=0, total=0;
    LinearLayout linearLayout;
    PopupWindow popupWindow;
    boolean loc =false;
    long secondRemain= 0;
    long millisUntilFinish;


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.ResultBoolean.setVisibility(View.GONE);




        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                if(secondRemain>0){
                    millisUntilFinished = millisUntilFinish;
                    binding.mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    secondRemain = 0;
                }
                else{
                    binding.mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    millisUntilFinish = millisUntilFinished;
                }

                if(right>0) {
                    if (right % 5 ==0) {
                        millisUntilFinished = millisUntilFinished + 10000;
                    }
                }
                if(millisUntilFinished<=10000){
                    binding.mTextField.setTextColor(Color.RED);
                }

            }

            public void onFinish() {
                binding.mTextField.setText("Time Over");
                binding.Lal.setVisibility(View.GONE);
                setPopupWindow();

            }
        }.start();


        binding.timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                binding.timer.setVisibility(View.GONE);
                binding.mTextField.setVisibility(View.GONE);
                binding.pause.setVisibility(View.GONE);
            }
        });


        binding.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.pause.getText().toString().equals("Pause")) {
                    countDownTimer.cancel();
                    binding.pause.setText("Continue Timer");
                    secondRemain = millisUntilFinish;
                }
                else{

                    countDownTimer.start();
                    binding.pause.setText("Pause");

                }
            }

        });

        binding.resultIs.setVisibility(View.GONE);
        binding.LastButtons.setVisibility(View.GONE);
        expressions();
        Calculation();
        binding.ShowResult.setOnClickListener(v -> {
            TextView Total, Right;
            View view = View.inflate( MainActivity.this, R.layout.pop_ups, null);
            Button close, end ;
            Total = view.findViewById(R.id.totals);
            Right = view.findViewById(R.id.rights);
            close = view.findViewById(R.id.closee);
            Total.setText("Total Attempted = " +total);
            Right.setText("Correct Answers = "+right);
            LinearLayout linearLayout = findViewById(R.id.Lal);
            popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,false);
            popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0,0);
            binding.Lal.setVisibility(View.GONE);
            close.setOnClickListener(v1 -> {
                popupWindow.dismiss();
                binding.Lal.setVisibility(View.VISIBLE);

            });
            end = view.findViewById(R.id.end);
            end.setOnClickListener(v12 -> MainActivity.this.finish());
        });
        binding.next.setOnClickListener(v -> {
            binding.ExpressionButtons.setVisibility(View.VISIBLE);
            binding.LastButtons.setVisibility(View.GONE);
            binding.ResultBoolean.setVisibility(View.GONE);
            binding.resultIs.setVisibility(View.GONE);
            binding.result.setText("First Expression is :");
            binding.result.setTextColor(R.color.pink);
            expressions();
            Calculation();

        });
        binding.finish.setOnClickListener(v -> {
        setPopupWindow();

        });


    }
    @SuppressLint("SetTextI18n")
    void expressions(){
        Random random = new Random();
        int xp = random.nextInt(3)+1;
        int xp1 = random.nextInt(3)+1;
        int number1 , number2 , number3, number4;
        String sign1, sign2, sign3;

        switch (xp){
            case 1:
                number1  = randomNumber();
                number2 = randomNumber();
                sign1 = randomSign();
                switch (sign1) {
                    case "+":
                        result1 = number1 + number2;
                        break;
                    case "-":
                        result1 = number1 - number2;
                        break;
                    case "*":
                        result1 = number1 * number2;
                        break;
                    default:
                        result1 = number1 / number2;
                        break;
                }
                binding.exp1.setText(number1+sign1+number2+"");
                break;

            case 2:
                number1  = randomNumber();
                number2 = randomNumber();
                number3 = randomNumber();
                sign1 = randomSign();
                sign2 = randomSign();
                switch (sign1) {
                    case "+":
                        result1 = number1 + number2;
                        break;
                    case "-":
                        result1 = number1 - number2;
                        break;
                    case "*":
                        result1 = number1 * number2;
                        break;
                    default:
                        result1 = number1 / number2;
                        break;
                }
                switch (sign2) {
                    case "+":
                        result1 = result1 + number3;
                        break;
                    case "-":
                        result1 = result1 - number3;
                        break;
                    case "*":
                        result1 = result1 * number3;
                        break;
                    default:
                        result1 = result1/ number3;
                        break;
                }
                binding.exp1.setText("("+number1+sign1+number2+")"+sign2+number3);
                break;

            case 3:
                number1  = randomNumber();
                number2 = randomNumber();
                number3 = randomNumber();
                number4 = randomNumber();
                sign1 = randomSign();
                sign3 = randomSign();
                sign2 = randomSign();
                switch (sign1) {
                    case "+":
                        result1 = number1 + number2;
                        break;
                    case "-":
                        result1 = number1 - number2;
                        break;
                    case "*":
                        result1 = number1 * number2;
                        break;
                    default:
                        result1 = number1 / number2;
                        break;
                }
                switch (sign2) {
                    case "+":
                        result1 = result1 + number3;
                        break;
                    case "-":
                        result1 = result1 - number3;
                        break;
                    case "*":
                        result1 = result1 * number3;
                        break;
                    default:
                        result1 = result1/ number3;
                        break;
                }
                switch (sign3) {
                    case "+":
                        result1 = result1 + number4;
                        break;
                    case "-":
                        result1 = result1 - number4;
                        break;
                    case "*":
                        result1 = result1 * number4;
                        break;
                    default:
                        result1 = result1/ number4;
                        break;
                }

                binding.exp1.setText("(("+number1+sign1+number2+")"+sign2+number3+")"+sign3+number4);
                break;
            default:
                number1 = randomNumber();
                binding.exp1.setText(number1+"");
        }

        switch (xp1){
            case 1:
                number1  = randomNumber();
                number2 = randomNumber();
                sign1 = randomSign();

                switch (sign1) {
                    case "+":
                        result2 = number1 + number2;
                        break;
                    case "-":
                        result2 = number1 - number2;
                        break;
                    case "*":
                        result2 = number1 * number2;
                        break;
                    default:
                        result2 = number1 / number2;
                        break;
                }

                binding.exp2.setText(number1+sign1+number2+"");
                break;

            case 2:
                number1  = randomNumber();
                number2 = randomNumber();
                number3 = randomNumber();
                sign1 = randomSign();
                sign2 = randomSign();
                switch (sign1) {
                    case "+":
                        result2 = number1 + number2;
                        break;
                    case "-":
                        result2 = number1 - number2;
                        break;
                    case "*":
                        result2 = number1 * number2;
                        break;
                    default:
                        result2 = number1 / number2;
                        break;
                }
                switch (sign2) {
                    case "+":
                        result2 = result2 + number3;
                        break;
                    case "-":
                        result2 = result2 - number3;
                        break;
                    case "*":
                        result2 = result2 * number3;
                        break;
                    default:
                        result2 = result2/ number3;
                        break;
                }

                binding.exp2.setText("("+number1+sign1+number2+")"+sign2+number3);
                break;

            case 3:
                number1  = randomNumber();
                number2 = randomNumber();
                number3 = randomNumber();
                number4 = randomNumber();
                sign1 = randomSign();
                sign3 = randomSign();
                sign2 = randomSign();
                switch (sign1) {
                    case "+":
                        result2 = number1 + number2;
                        break;
                    case "-":
                        result2 = number1 - number2;
                        break;
                    case "*":
                        result2 = number1 * number2;
                        break;
                    default:
                        result2 = number1 / number2;
                        break;
                }
                switch (sign2) {
                    case "+":
                        result2 = result2 + number3;
                        break;
                    case "-":
                        result2 = result2 - number3;
                        break;
                    case "*":
                        result2 = result2 * number3;
                        break;
                    default:
                        result2 = result2/ number3;
                        break;
                }
                switch (sign3) {
                    case "+":
                        result2 = result2 + number4;
                        break;
                    case "-":
                        result2 = result2 - number4;
                        break;
                    case "*":
                        result2 = result2 * number4;
                        break;
                    default:
                        result2 = result2/ number4;
                        break;
                }
                binding.exp2.setText("(("+number1+sign1+number2+")"+sign2+number3+")"+sign3+number4);
                break;
            default:
                number1 = randomNumber();
                binding.exp1.setText(number1+"");
        }
    }
    int randomNumber(){
        Random random =  new Random();
        return random.nextInt(20)+1;
    }
    @SuppressLint("SetTextI18n")
    void Calculation(){

        binding.greater.setOnClickListener(v -> {
            binding.ResultBoolean.setVisibility(View.VISIBLE);
            binding.resultIs.setVisibility(View.VISIBLE);
            binding.result.setVisibility(View.VISIBLE);
            total++;
            if(result1>result2){
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" > "+binding.exp2.getText().toString());
                binding.ExpressionButtons.setVisibility(View.GONE);
                binding.LastButtons.setVisibility(View.VISIBLE);
                loc=true;
                right++;

            }
            else if(result1<result2){
                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                binding.result.setText(binding.exp1.getText().toString()+" < "+binding.exp2.getText().toString());
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setTextColor(Color.RED);
                binding.LastButtons.setVisibility(View.VISIBLE);
                loc=false;
                binding.ExpressionButtons.setVisibility(View.GONE);
            }
            else{
                Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                binding.result.setText(binding.exp1.getText().toString()+" = "+binding.exp2.getText().toString());
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.LastButtons.setVisibility(View.VISIBLE);
                loc=false;
                binding.result.setTextColor(Color.RED);
                binding.ExpressionButtons.setVisibility(View.GONE);
            }

            if(loc){
                binding.ResultBoolean.setVisibility(View.VISIBLE);
                binding.ResultBoolean.setText("Correct");
                binding.ResultBoolean.setTextColor(Color.GREEN);

            }
            else{
                binding.ResultBoolean.setVisibility(View.VISIBLE);
                binding.ResultBoolean.setText("Wrong");
                binding.ResultBoolean.setTextColor(Color.RED);
            }
        });
        binding.less.setOnClickListener(v -> {
            total++;

            binding.ResultBoolean.setVisibility(View.VISIBLE);
            binding.resultIs.setVisibility(View.VISIBLE);
            binding.result.setVisibility(View.VISIBLE);

            if(result1<result2){
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" < "+binding.exp2.getText().toString());
                binding.ExpressionButtons.setVisibility(View.GONE);
                binding.LastButtons.setVisibility(View.VISIBLE);
                loc=true;
                right++;
            }
            else if(result1>result2){
                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" > "+binding.exp2.getText().toString());
                binding.result.setTextColor(Color.RED);
                binding.LastButtons.setVisibility(View.VISIBLE);
                binding.ExpressionButtons.setVisibility(View.GONE);
                loc=false;

            }
            else{
                Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" = "+binding.exp2.getText().toString());
                binding.result.setTextColor(Color.RED);
                binding.LastButtons.setVisibility(View.VISIBLE);

                binding.ExpressionButtons.setVisibility(View.GONE);
                loc=false;
            }
            if(loc){
                binding.ResultBoolean.setVisibility(View.VISIBLE);
                binding.ResultBoolean.setText("Correct");
                binding.ResultBoolean.setTextColor(Color.GREEN);

            }
            else{
                binding.ResultBoolean.setVisibility(View.VISIBLE);
                binding.ResultBoolean.setText("Wrong");
                binding.ResultBoolean.setTextColor(Color.RED);
            }

        });
        binding.equal.setOnClickListener(v -> {

            binding.ResultBoolean.setVisibility(View.VISIBLE);
            binding.resultIs.setVisibility(View.VISIBLE);
            binding.result.setVisibility(View.VISIBLE);

            total++;
            if(result1==result2){
                right++;
                binding.LastButtons.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" = "+binding.exp2.getText().toString());
                binding.ExpressionButtons.setVisibility(View.GONE);
                loc = true;
            }
            else if(result1>result2){
                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" > "+binding.exp2.getText().toString());
                binding.result.setTextColor(Color.RED);
                binding.LastButtons.setVisibility(View.VISIBLE);
                loc=false;
                binding.ExpressionButtons.setVisibility(View.GONE);

            }
            else{
                Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                binding.resultIs.setVisibility(View.VISIBLE);
                binding.result.setText(binding.exp1.getText().toString()+" < "+binding.exp2.getText().toString());
                binding.result.setTextColor(Color.RED);
                binding.LastButtons.setVisibility(View.VISIBLE);
                loc=false;
                binding.ExpressionButtons.setVisibility(View.GONE);
            }

            if(loc){
                binding.ResultBoolean.setVisibility(View.VISIBLE);
                binding.ResultBoolean.setText("Correct");
                binding.ResultBoolean.setTextColor(Color.GREEN);
            }
            else{
                binding.ResultBoolean.setVisibility(View.VISIBLE);
                binding.ResultBoolean.setText("Wrong");
                binding.ResultBoolean.setTextColor(Color.RED);
            }
        });

    }
    @SuppressLint("SetTextI18n")
    void setPopupWindow(){

        TextView Total, Right;
        View view = View.inflate( MainActivity.this, R.layout.pop_ups, null);
        Button close , end;
        end = view.findViewById(R.id.end);
        Total = view.findViewById(R.id.totals);
        Right = view.findViewById(R.id.rights);
        close = view.findViewById(R.id.closee);
        close.setVisibility(View.GONE);
        Total.setText("Total Attempted = " +total);
        Right.setText("Correct Answers = "+right);
        linearLayout = findViewById(R.id.Lal);
        PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,false);
        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0,0);
        binding.Lal.setVisibility(View.GONE);
        end.setOnClickListener(v13 -> {
            popupWindow.dismiss();
            MainActivity.this.finish();
        });
    }
    String randomSign(){
        Random random = new Random();

        String sign1 ;

        String plus = "+";
        String minus = "-";
        String divide = "/";
        String multiply="*";

        int expr1 = random.nextInt(4)+1;
        switch (expr1){
            case 1:
                sign1 =  plus;
               return sign1;
            case 2:
                sign1 = minus ;
                return sign1  ;
            case 3:
                sign1 = multiply ;
                return sign1 ;
            case 4:
                sign1 = divide;
               return sign1 ;
            default:
                return "%";
        }
    }
}