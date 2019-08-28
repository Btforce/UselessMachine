package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Switch uselessSwitch;
private Button selfDestruct;
private Button lookBusy;
private ProgressBar progress;
private ConstraintLayout contstraintLayout;
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

            }
        });

        lookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        contstraintLayout = findViewById(R.id.constraintLayout_main_constraintLayout);
    }


}
