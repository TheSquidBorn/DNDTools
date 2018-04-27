package com.example.erik.dndtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class ArmyToolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armytool);

        Button goButton = findViewById(R.id.goButton);
        Button acButton = findViewById(R.id.acButton);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText boysText = findViewById(R.id.boysText);
                EditText attackText = findViewById(R.id.attackText);
                EditText bonusText = findViewById(R.id.bonusText);
                EditText acText = findViewById(R.id.acText);

                TextView resultTextView = findViewById(R.id.resultTextView);

                Switch packBonusSwitch = findViewById(R.id.packBonusSwitch);

                String[] numbers = new String[2];

                Random rng = new Random();

                int boys;
                int bonus;
                int ac;
                int crit = 0;
                int hit = 0;
                int roll;
                int roll2;
                int damage = 0;
                int i;
                int attack = 0;
                int rolls;
                int maxSide;
                int attackBonus;
                int n;
                int dmgRoll;
                int totalAttack = 0;

                boys = Integer.parseInt(boysText.getText().toString());
                bonus = Integer.parseInt(bonusText.getText().toString());
                ac = Integer.parseInt(acText.getText().toString());

                if (tryParseInt(attackText.getText().toString())){
                    attack = Integer.parseInt(attackText.getText().toString());
                } else {
                    String data = attackText.getText().toString();

                    data = data.replaceAll("\\+", "d");
                    numbers = data.split("d");
                }

                ac -= (1 + bonus);

                boolean packBonus = false;
                if (packBonusSwitch.isChecked() & boys > 3){
                    packBonus = true;
                }

                while (boys != 0){
                    roll = rng.nextInt(21);

                    if (packBonus){
                        roll2 = rng.nextInt(21);

                        if(roll2 > roll){
                            roll = roll2;
                        }
                    }

                    if (roll == 20){
                        crit++;
                        hit++;
                    } else if (roll > ac){
                        hit++;
                    }

                    boys--;
                }
                if (attack == 0){
                    i = (hit + crit);

                    rolls = Integer.parseInt(numbers[0]);
                    maxSide = (Integer.parseInt(numbers[1]) + 1);
                    if (numbers.length == 2) {
                        attackBonus = 0;
                    } else {
                        attackBonus = Integer.parseInt(numbers[2]);
                    }

                    while (i != 0){
                        n = rolls;
                        while(n != 0) {
                            dmgRoll = rng.nextInt(maxSide);
                            totalAttack += dmgRoll;
                            n--;
                        }
                        totalAttack += attackBonus;
                        if(crit != 0){
                            totalAttack = totalAttack * 2;
                            crit--;
                        }
                        damage += totalAttack;
                        totalAttack = 0;
                        i--;
                    }
                }else{
                    damage = (hit + crit) * attack;
                }
                resultTextView.setText(String.valueOf(damage));
            }

            boolean tryParseInt(String value) {
                try {
                    Integer.parseInt(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });

        acButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ac_Activity.class);

                startActivity(startIntent);
            }
        });
    }
}