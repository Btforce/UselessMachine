package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Switch uselessSwitch;
private Button selfDestruct;
private Button lookBusy;
private ProgressBar progress;
private ConstraintLayout constraintLayout;
private TextView textViewProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
    }

    private void setListeners() {
        selfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set 10 second count down timer
                //display how much time is left on the countdown on the button
                //when the timer is complete, call the finish method to close the activity
                new CountDownTimer(10000, 300) {
                    int counter = 0;
                    int multiplier = 0;
                    boolean colored = false;
                    int subtract = 1;

                    @Override
                    public void onFinish() {
                        finish();
                    }

                    @Override
                    public void onTick(long l) {
                        selfDestruct.setText(String.valueOf((int) (l / 1000) + 1));
                        counter++;
                        if (l / 1000 % 5 == 0) {
                            multiplier++;
                            subtract = 30 * multiplier;
                        }
                        if (!colored && counter % 2 == 0) {
                            new CountDownTimer(300 - subtract, 300 - subtract) {

                                @Override
                                public void onTick(long l) {
                                    int red = 255;
                                    int blue = 0;
                                    int green = 0;
                                    int r = Color.rgb(red, green, blue);
                                    constraintLayout.setBackgroundColor(r);
                                    colored = true;
                                }

                                @Override
                                public void onFinish() {
                                    int r = 255;
                                    int b = 255;
                                    int g = 255;
                                    int original = Color.rgb(r,g,b);
                                    constraintLayout.setBackgroundColor(original);
                                    colored = false;
                                }
                            }.start();
                        }
                    }
                }.start();
            }
        });


        lookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lookBusy.setVisibility(View.GONE);
                uselessSwitch.setVisibility(View.GONE);
                selfDestruct.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                textViewProgress.setVisibility(View.VISIBLE);

                new CountDownTimer(5000, 47) {
                    @Override
                    public void onTick(long l) {
                        progress.incrementProgressBy(1);
                        textViewProgress.setText(String.valueOf(progress.getProgress())+ "%");
                    }

                    @Override
                    public void onFinish() {
                        lookBusy.setVisibility(View.VISIBLE);
                        uselessSwitch.setVisibility(View.VISIBLE);
                        selfDestruct.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                        textViewProgress.setVisibility(View.GONE);
                        progress.setProgress(0);

                    }
                }.start();

            }
        });

        uselessSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                if(isChecked) {
                    new CountDownTimer(2000, 10) {
                        @Override
                        public void onTick(long l) {
                            if(!uselessSwitch.isChecked()){
                                cancel();
                            }
                        }

                        @Override
                        public void onFinish() {
                            uselessSwitch.setChecked(false);
                        }
                    }.start();
                }
//                if(isChecked){Toast.makeText(MainActivity.this, ":)", Toast.LENGTH_SHORT).show();}
//                else {
//                    Toast.makeText(MainActivity.this, "):", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void wireWidgets() {
        uselessSwitch = findViewById(R.id.switch_main_switcher);
        selfDestruct = findViewById(R.id.button_main_selfDestruct);
        lookBusy = findViewById(R.id.button_main_lookBusy);
        progress = findViewById(R.id.progressBar_main_progress);
        constraintLayout = findViewById(R.id.constraintLayout_main_constraintLayout);
        textViewProgress = findViewById(R.id.textView_main_progress);
    }


}
