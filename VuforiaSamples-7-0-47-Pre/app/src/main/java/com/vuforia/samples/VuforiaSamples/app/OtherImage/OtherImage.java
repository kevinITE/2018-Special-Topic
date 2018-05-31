package com.vuforia.samples.VuforiaSamples.app.OtherImage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.vuforia.samples.VuforiaSamples.R;

public class OtherImage extends AppCompatActivity {

    private ImageView intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_image);

        intro = (ImageView)findViewById(R.id.intro);
        intro.setImageResource(R.drawable.far);
        Intent intent = this.getIntent();
        int name = intent.getIntExtra("name", 0);
        switch(name){
            case 0:
                intro.setImageResource(R.drawable.far);
                break;
            case 1:
                intro.setImageResource(R.drawable.fish);
                break;
            case 2:
                intro.setImageResource(R.drawable.farm);
                break;
            case 3:
                intro.setImageResource(R.drawable.eastdoor);
                break;
            case 4:
                intro.setImageResource(R.drawable.know);
                break;
            case 5:
                intro.setImageResource(R.drawable.cloud);
                break;
            case 6:
                intro.setImageResource(R.drawable.white);
                break;
            case 7:
                intro.setImageResource(R.drawable.tain);
                break;
            default:
                intro.setImageResource(R.drawable.far);
                break;
        }
    }
}
