package com.example.erik.dndtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class ArmySave extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armysave);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText boysText = findViewById(R.id.boysText);
                EditText dcText = findViewById(R.id.dcText);
                EditText bonusText = findViewById(R.id.bonusText);

                TextView resultText = findViewById(R.id.resultText);

                Switch advantageSwitch = findViewById(R.id.advantageSwitch);

                int boys;
                int dc;
                int bonus;
                int save = 0;
                int fail = 0;
                int roll0;
                int roll1;

                String result;

                boolean advantage;

                Random rng = new Random();

                boys = Integer.parseInt(boysText.getText().toString());
                dc = Integer.parseInt(dcText.getText().toString());
                bonus = Integer.parseInt(bonusText.getText().toString());

                advantage = advantageSwitch.isChecked();

                dc -= bonus + 1;

                while (boys != 0){
                    roll0 = rng.nextInt(21);
                    if (advantage){
                        roll1 = rng.nextInt(21);
                        if(roll0 < roll1){
                            roll0 = roll1;
                        }
                    }

                    if (roll0 == 20){
                        save++;
                    } else if(roll0 > dc) {
                        save++;
                    } else {
                        fail++;
                    }
                    boys--;
                }

                result = ("Saves: " + String.valueOf(save) + " Fails: " + String.valueOf(fail));

                resultText.setText(result);
            }
        });
    }
}
