package com.example.erik.dndtools;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ArmyTool = findViewById(R.id.armyToolButton);
        Button SaveTool = findViewById(R.id.armySaveButton);
        Button SplitTool = findViewById(R.id.splitButton);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.neversplit);

        ArmyTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ArmyToolActivity.class);
                startActivity(startIntent);
            }
        });

        SaveTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ArmySave.class);
                startActivity(startIntent);
            }
        });

        SplitTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()){
                    mp.pause();
                    mp.seekTo(0);
                }
                else {
                    mp.start();
                }
            }
        });
     }
}