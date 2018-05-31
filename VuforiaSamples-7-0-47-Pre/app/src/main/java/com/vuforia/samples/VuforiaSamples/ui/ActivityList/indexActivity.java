package com.vuforia.samples.VuforiaSamples.ui.ActivityList;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.VuforiaSamples.app.About.About;
import com.vuforia.samples.VuforiaSamples.app.Food.Food;
import com.vuforia.samples.VuforiaSamples.app.Living.Living;
import com.vuforia.samples.VuforiaSamples.app.Other.Other;
import com.vuforia.samples.VuforiaSamples.app.Transfer.Transfer;

public class indexActivity extends AppCompatActivity {

    private Button tourism, transfer, food, living, others, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);

        tourism = (Button)findViewById(R.id.tourism);
        transfer = (Button)findViewById(R.id.transfer);
        food = (Button)findViewById(R.id.food);
        living = (Button)findViewById(R.id.living);
        others = (Button)findViewById(R.id.others);
        about = (Button)findViewById(R.id.about);

        tourism.setOnClickListener(tourism_click);
        transfer.setOnClickListener(transfer_click);
        food.setOnClickListener(food_click);
        living.setOnClickListener(living_click);
        others.setOnClickListener(others_click);
        about.setOnClickListener(about_click);

   /*     btnGo = (Button) findViewById(R.id.btn_start);
        btnGo.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Skip ActivityLauncher
                Intent intent = new Intent(indexActivity.this, AboutScreen.class);
                intent.putExtra("ABOUT_TEXT_TITLE", "Maps Activity");
                //Intent intent = new Intent(indexActivity.this,  ActivityLauncher.class);

                intent.putExtra("ACTIVITY_TO_LAUNCH",
                        "app.MapsActivity.MapsActivity");
                intent.putExtra("ABOUT_TEXT", "MapsActivity/M_about.html");
                startActivity(intent);
            }
        });*/
    }

    private Button.OnClickListener tourism_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(indexActivity.this, AboutScreen.class);
            intent.putExtra("ABOUT_TEXT_TITLE", "Maps Activity");

            intent.putExtra("ACTIVITY_TO_LAUNCH",
                    "app.MapsActivity.MapsActivity");
            intent.putExtra("ABOUT_TEXT", "MapsActivity/M_about.html");
            startActivity(intent);
        }
    };

    private Button.OnClickListener transfer_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(indexActivity.this, Transfer.class);
            startActivity(i);
        }
    };

    private Button.OnClickListener food_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(indexActivity.this, Food.class);
            startActivity(i);
        }
    };

    private Button.OnClickListener living_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(indexActivity.this, Living.class);
            startActivity(i);
        }
    };

    private Button.OnClickListener others_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(indexActivity.this, Other.class);
            startActivity(i);
        }
    };

    private Button.OnClickListener about_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(indexActivity.this, About.class);
            startActivity(i);
        }
    };

}
