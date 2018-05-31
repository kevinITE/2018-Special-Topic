/*===============================================================================
Copyright (c) 2016 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/

package com.vuforia.samples.VuforiaSamples.ui.ActivityList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.vuforia.samples.VuforiaSamples.R;


public class AboutScreen extends Activity implements OnClickListener
{
    private static final String LOGTAG = "AboutScreen";
    
    //private WebView mAboutWebText;

   // private ListView lv_spot;
    private Button mStartButton;
    private TextView mAboutTextTitle;
    private String mClassToLaunch;
    private String mClassToLaunchPackage;

    Button btn_all, btn_study, btn_site, btn_res;
    ImageView mImage;
    private ListView listview;
    public String[] str_restaurant = {"人文社會科學院餐廳","管理學院餐廳","湖畔餐廳","多容館"};
    public String[] str_study = {"原民院","花師院","理工學院","環境學院","管理學院"};
    public String[] str_site = {"向晴球場","操場","泳池","棒壘球場","行政大樓"};
    public String[] str_all = {"人文社會科學院餐廳","管理學院餐廳","湖畔餐廳","多容館","原民院","花師院","理工學院","環境學院","向晴球場","泳池","棒壘球場","行政大樓","操場", "自訂"};
    double f_value, s_value; // latitude, longitude
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        f_value = s_value = 0.0;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.about_screen);
        
        Bundle extras = getIntent().getExtras();
        //String webText = extras.getString("ABOUT_TEXT");
        mClassToLaunchPackage = getPackageName();
        mClassToLaunch = mClassToLaunchPackage + "."
            + extras.getString("ACTIVITY_TO_LAUNCH");
/*
        lv_spot = (ListView) findViewById(R.id.lstView_spot);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activities_list_text_view, spots);

        lv_spot.setAdapter(adapter);
        lv_spot.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
*/
       /* String aboutText = "";
        try
        {
            InputStream is = getAssets().open(webText);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                aboutText += line;
            }
        } catch (IOException e)
        {
            Log.e(LOGTAG, "About html loading failed");
        }
        
        mAboutWebText.loadData(aboutText, "text/html", "UTF-8");
        */
        
        mStartButton = (Button) findViewById(R.id.button_start);
        mStartButton.setOnClickListener(this);
        
  /*      mAboutTextTitle = (TextView) findViewById(R.id.about_text_title);
        mAboutTextTitle.setText(extras.getString("ABOUT_TEXT_TITLE"));
*/
        mImage = (ImageView)findViewById(R.id.mImage);
        mImage.setImageResource(R.drawable.people);
        listview = (ListView) findViewById(R.id.mlist);
        btn_all = (Button)findViewById(R.id.btn_all);
        btn_study = (Button)findViewById(R.id.btn_study);
        btn_site = (Button)findViewById(R.id.btn_site);
        btn_res = (Button)findViewById(R.id.btn_res);
        btn_all.setOnClickListener(btncheck);
        btn_study.setOnClickListener(btncheck);
        btn_site.setOnClickListener(btncheck);
        btn_res.setOnClickListener(btncheck);
        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 ,str_all);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(listviewlistener);

        /* start button onClick use (f_value, s_value)  */

    }

    private AdapterView.OnItemClickListener listviewstudy = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    mImage.setImageResource(R.drawable.hangi);
                    f_value = 23.895723;
                    s_value = 121.546280;
                    break;
                case 1:
                    mImage.setImageResource(R.drawable.teacher);
                    f_value = 23.895961;
                    s_value = 121.539188;
                    break;
                case 2:
                    mImage.setImageResource(R.drawable.engineer);
                    f_value = 23.899700;
                    s_value = 121.544301;
                    break;
                case 3:
                    mImage.setImageResource(R.drawable.environment);
                    f_value = 23.895969;
                    s_value = 121.548661;
                    break;
                case 4:
                    mImage.setImageResource(R.drawable.manager);
                    f_value = 23.897768;
                    s_value = 121.540212;
                    break;
                default:
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener listviewsite = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    mImage.setImageResource(R.drawable.basketball);
                    f_value = 23.899171;
                    s_value = 121.534843;
                    break;
                case 1:
                    mImage.setImageResource(R.drawable.playground);
                    f_value = 23.902585;
                    s_value = 121.537701;
                    break;
                case 2:
                    mImage.setImageResource(R.drawable.swim);
                    f_value = 23.900068;
                    s_value = 121.536634;
                    break;
                case 3:
                    mImage.setImageResource(R.drawable.baseball);
                    f_value = 23.902217;
                    s_value = 121.535161;
                    break;
                case 4:
                    mImage.setImageResource(R.drawable.thing);
                    f_value = 23.894289;
                    s_value = 121.543373;
                    break;
                default:
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener listviewres = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    mImage.setImageResource(R.drawable.people);
                    f_value = 23.895168;
                    s_value = 121.541634;
                    break;
                case 1:
                    mImage.setImageResource(R.drawable.manager);
                    f_value = 23.897923;
                    s_value = 121.539918;
                    break;
                case 2:
                    mImage.setImageResource(R.drawable.lake);
                    f_value = 23.896413;
                    s_value = 121.543754;
                    break;
                case 3:
                    mImage.setImageResource(R.drawable.multi);
                    f_value = 23.895264;
                    s_value = 121.535454;
                    break;
                default:
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener listviewlistener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    mImage.setImageResource(R.drawable.people);
                    f_value = 23.895168;
                    s_value = 121.541634;
                    break;
                case 1:
                    mImage.setImageResource(R.drawable.manager);
                    f_value = 23.897923;
                    s_value = 121.539918;
                    break;
                case 2:
                    mImage.setImageResource(R.drawable.lake);
                    f_value = 23.896413;
                    s_value = 121.543754;
                    break;
                case 3:
                    mImage.setImageResource(R.drawable.multi);
                    f_value = 23.895264;
                    s_value = 121.535454;
                    break;
                case 4:
                    mImage.setImageResource(R.drawable.hangi);
                    f_value = 23.895723;
                    s_value = 121.546280;
                    break;
                case 5:
                    mImage.setImageResource(R.drawable.teacher);
                    f_value = 23.895961;
                    s_value = 121.539188;
                    break;
                case 6:
                    mImage.setImageResource(R.drawable.engineer);
                    f_value = 23.899700;
                    s_value = 121.544301;
                    break;
                case 7:
                    mImage.setImageResource(R.drawable.environment);
                    f_value = 23.895969;
                    s_value = 121.548661;
                    break;
                case 8:
                    mImage.setImageResource(R.drawable.basketball);
                    f_value = 23.899171;
                    s_value = 121.534843;
                    break;
                case 9:
                    mImage.setImageResource(R.drawable.swim);
                    f_value = 23.900068;
                    s_value = 121.536634;
                    break;
                case 10:
                    mImage.setImageResource(R.drawable.baseball);
                    f_value = 23.902217;
                    s_value = 121.535161;
                    break;
                case 11:
                    mImage.setImageResource(R.drawable.thing);
                    f_value = 23.894289;
                    s_value = 121.543373;
                    break;
                case 12:
                    mImage.setImageResource(R.drawable.playground);
                    f_value = 23.902585;
                    s_value = 121.537701;
                    break;
                default:
                    f_value = s_value = 0.0;
                    break;
            }
        }
    };

    private Button.OnClickListener btncheck = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            ListAdapter adapter;
            switch(view.getId()){
                case R.id.btn_all:
                    adapter = new ArrayAdapter<>(AboutScreen.this , android.R.layout.simple_list_item_1 ,str_all);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(listviewlistener);
                    break;
                case R.id.btn_study:
                    adapter = new ArrayAdapter<>(AboutScreen.this , android.R.layout.simple_list_item_1 ,str_study);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(listviewstudy);
                    break;
                case R.id.btn_site:
                    adapter = new ArrayAdapter<>(AboutScreen.this , android.R.layout.simple_list_item_1 ,str_site);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(listviewsite);
                    break;
                case R.id.btn_res:
                    adapter = new ArrayAdapter<>(AboutScreen.this , android.R.layout.simple_list_item_1 ,str_restaurant);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(listviewres);
                    break;
                default:
                    break;
            }
        }
    };
    
    
    // Starts the chosen activity
    private void startARActivity()
    {
        Intent i = new Intent();
        i.setClassName(mClassToLaunchPackage, mClassToLaunch);

        // Send the target latitude and longitude
        Bundle bundle = new Bundle();
        LatLng loc = new LatLng(f_value, s_value);
        bundle.putParcelable("location", loc);
        i.putExtra("bundle", bundle);

        startActivity(i);
    }
    
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_start:
                startARActivity();
                break;
        }
    }
    /*
    private class AboutWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }*/
}
