package com.panotti.traficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout b_1, b_2, b_3;
    private Button button1;
    private int counter = 0;
    private boolean key = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_1 = findViewById(R.id.bulb_1green);
        b_2 = findViewById(R.id.bulb_2hellow);
        b_3 = findViewById(R.id.bulb_3red);
        button1 = findViewById(R.id.buttonStrartStop);
    }

    public void onClickStart(View view) {
        if (!key){
            key = true;
            button1.setText("Stop! ");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (key){
                        counter++;
                        try {
                            Thread.sleep(500);
                            switch (counter) {
                                case 1:
                                    b_3.setBackgroundColor(getResources().getColor((R.color.grayDefaultFlag)));
                                    b_1.setBackgroundColor(getResources().getColor(R.color.redFlag));
                                    break;
                                case 2:
                                    b_1.setBackgroundColor(getResources().getColor((R.color.grayDefaultFlag)));
                                    b_2.setBackgroundColor(getResources().getColor((R.color.yellowFlag)));
                                    break;
                                case 3:
                                    b_2.setBackgroundColor(getResources().getColor((R.color.grayDefaultFlag)));
                                    b_3.setBackgroundColor(getResources().getColor((R.color.greenFlag)));
                                    counter = 0;
                                    break;
                            }
//                            b_1.setBackgroundColor(R.);
//                            b_2.setBackgroundColor(R.id.bulb_2hellow);
//                            b_3.setBackgroundColor(R.id.bulb_3red);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        } else {
            key = false;
            button1.setText("Start!");
        }
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        key = false;
    }
}