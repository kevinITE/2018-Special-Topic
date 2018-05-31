package com.vuforia.samples.VuforiaSamples.app.Other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.VuforiaSamples.app.OtherImage.OtherImage;

public class Other extends AppCompatActivity {

    private Button far, fish, farm, eastdoor, know, cloud, white, tain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        far = (Button) findViewById(R.id.far);
        fish = (Button) findViewById(R.id.fish);
        farm = (Button) findViewById(R.id.farm);
        eastdoor = (Button) findViewById(R.id.eastdoor);
        know = (Button) findViewById(R.id.know);
        cloud = (Button) findViewById(R.id.cloud);
        white = (Button) findViewById(R.id.white);
        tain = (Button) findViewById(R.id.tain);
        far.setOnClickListener(click);
        fish.setOnClickListener(click);
        farm.setOnClickListener(click);
        eastdoor.setOnClickListener(click);
        know.setOnClickListener(click);
        cloud.setOnClickListener(click);
        white.setOnClickListener(click);
        tain.setOnClickListener(click);
    }

    private Button.OnClickListener click = new Button.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent i = new Intent(Other.this, OtherImage.class);
            switch (view.getId()){
                case R.id.far:
                    i.putExtra("name", 0);
                    startActivity(i);
                    break;
                case R.id.fish:
                    i.putExtra("name", 1);
                    startActivity(i);
                    break;
                case R.id.farm:
                    i.putExtra("name", 2);
                    startActivity(i);
                    break;
                case R.id.eastdoor:
                    i.putExtra("name", 3);
                    startActivity(i);
                    break;
                case R.id.know:
                    i.putExtra("name", 4);
                    startActivity(i);
                    break;
                case R.id.cloud:
                    i.putExtra("name", 5);
                    startActivity(i);
                    break;
                case R.id.white:
                    i.putExtra("name", 6);
                    startActivity(i);
                    break;
                case R.id.tain:
                    i.putExtra("name", 7);
                    startActivity(i);
                    break;
            }
        }
    };
}
