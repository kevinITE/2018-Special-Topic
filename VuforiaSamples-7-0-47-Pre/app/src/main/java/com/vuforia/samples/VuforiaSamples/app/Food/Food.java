package com.vuforia.samples.VuforiaSamples.app.Food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vuforia.samples.VuforiaSamples.R;

public class Food extends AppCompatActivity {

    ImageView mImage;
    ListView mlist;
    Button btn_breakfast, btn_easy, btn_dumpling, btn_else;
    public String[] str_breakfast = {"麥味登 (03)866-1237", "萬家鄉 (03)866-2225", "瑞麟(東華店) 0937-014-069",
            "ZERO 0905-494-185 ","麥樂多 0919-250-789 ","呼朋引伴 0988-489-059","哈拉早午餐 (03)866-2788"};
    public String[] str_dumpling = {"朱緣 0912-212-248","八方雲集 (03)866-2298","水餃肉粽伯 0979-797-958","賀田屋 (03)866-2095","花蓮鄉 (03)866-3668",
            "魚包蛋 (03)866-3219","羹大王 (03)8662906"};
    public String[] str_easy = {"大上海 (03)866-1550","竹堤小舍 (03)866-3772","吮八方 (03)866-2906","好佳小吃 (03)866-2708","睿饌坊 0910-201-546",
            "港龍燒臘 (03)866-3901","極鮮坊 (03)-866-4269","NO.49 (03)866-3349"};
    public String[] str_else = {"優派雞排 0965-113-515","家鄉雞排 (03)866-3712","貴族派雞排 (03)866-1498","東華牛排 (03)866-2114","鍋神 (03)866-3250",
            "大呼過癮 (03)866-3789","阿江豆花 0935-588-480"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        btn_breakfast = (Button) findViewById(R.id.btn_breakfast);
        btn_easy = (Button) findViewById(R.id.btn_easy);
        btn_dumpling = (Button) findViewById(R.id.btn_dumpling);
        btn_else = (Button) findViewById(R.id.btn_else);
        btn_breakfast.setOnClickListener(btncheck);
        btn_easy.setOnClickListener(btncheck);
        btn_dumpling.setOnClickListener(btncheck);
        btn_else.setOnClickListener(btncheck);

        mImage = (ImageView) findViewById(R.id.myImage);
        mImage.setImageResource(R.drawable.foodmap);
        mlist = (ListView) findViewById(R.id.mlist);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str_breakfast);
        mlist.setAdapter(adapter);
    }

    private Button.OnClickListener btncheck = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            ListAdapter adapter;
            switch(view.getId()){
                case R.id.btn_breakfast:
                    adapter = new ArrayAdapter<>(Food.this , android.R.layout.simple_list_item_1 ,str_breakfast);
                    mlist.setAdapter(adapter);
                    break;
                case R.id.btn_easy:
                    adapter = new ArrayAdapter<>(Food.this , android.R.layout.simple_list_item_1 ,str_easy);
                    mlist.setAdapter(adapter);
                    break;
                case R.id.btn_dumpling:
                    adapter = new ArrayAdapter<>(Food.this , android.R.layout.simple_list_item_1 ,str_dumpling);
                    mlist.setAdapter(adapter);
                    break;
                case R.id.btn_else:
                    adapter = new ArrayAdapter<>(Food.this , android.R.layout.simple_list_item_1 ,str_else);
                    mlist.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };
}
