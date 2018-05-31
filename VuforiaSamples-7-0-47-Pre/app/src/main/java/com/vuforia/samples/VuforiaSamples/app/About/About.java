package com.vuforia.samples.VuforiaSamples.app.About;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.vuforia.samples.VuforiaSamples.R;

public class About extends AppCompatActivity {

    ImageView image_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        image_about = (ImageView)findViewById(R.id.image_about);
        image_about.setImageResource(R.drawable.about);
    }
}
