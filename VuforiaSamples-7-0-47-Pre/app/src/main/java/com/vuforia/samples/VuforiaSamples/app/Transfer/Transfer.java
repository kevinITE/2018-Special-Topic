package com.vuforia.samples.VuforiaSamples.app.Transfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vuforia.samples.VuforiaSamples.R;

public class Transfer extends AppCompatActivity {

    private Button button_f, button_s;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        image = (ImageView)findViewById(R.id.image);
        image.setImageResource(R.drawable.transfer);
        button_f = (Button)findViewById(R.id.button_f);
        button_s = (Button)findViewById(R.id.button_s);
        button_f.setOnClickListener(click);
        button_s.setOnClickListener(click);
    }

    private Button.OnClickListener click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button_f:
                    image.setImageResource(R.drawable.transfer);
                    break;
                case R.id.button_s:
                    image.setImageResource(R.drawable.transfer_1);
                    break;
                default:
                    break;
            }
        }
    };
}
