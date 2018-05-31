package com.vuforia.samples.VuforiaSamples.app.iBeaconDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.vuforia.samples.VuforiaSamples.R;

public class iBeaconDetail extends AppCompatActivity {

    private ImageView intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_beacon_detail);

        intro = (ImageView) findViewById(R.id.imageView);
        Intent mIntent = getIntent();
        int value = mIntent.getIntExtra("major", 0);
        switch(value){
            case 24372:
                intro.setImageResource(R.drawable.image_x);
                break;
            case 15682:
                intro.setImageResource(R.drawable.image_y);
                break;
            case 53460:
                intro.setImageResource(R.drawable.image_z);
                break;
            case 59300:
                intro.setImageResource(R.drawable.image_w);
                break;
            default:
                break;
        }
    }
}
