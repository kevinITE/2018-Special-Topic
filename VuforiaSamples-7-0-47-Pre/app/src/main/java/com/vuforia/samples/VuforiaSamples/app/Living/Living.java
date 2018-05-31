package com.vuforia.samples.VuforiaSamples.app.Living;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vuforia.samples.VuforiaSamples.R;

public class Living extends AppCompatActivity {

    private ImageView scene;
    private TextView text;
    private Button web;
    private int position_b = 0;
    private ListView list;
    public String[] str = {"東華會館","理想大地","村上村樹","遠雄悅來","晨風星語","碧海藍天","函園旅店"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);

        scene = (ImageView)findViewById(R.id.scene);
        scene.setImageResource(R.drawable.ndhu);
        web = (Button)findViewById(R.id.web);
        web.setOnClickListener(web_click);
        text = (TextView)findViewById(R.id.text);
        text.setText("地點:花蓮縣壽豐鄉志學村大學路二段1號974" + "\n\n" + "電話:(03)8630103" + "\n" + "距火車站4.3公里" + "\n\n" + "推薦: ☆☆☆☆");
        list = (ListView)findViewById(R.id.list);
        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 ,str);
        list.setAdapter(adapter);
        list.setOnItemClickListener(listview_listener);
    }

    private AdapterView.OnItemClickListener listview_listener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position_b = position;
            switch (position){
                case 0:
                    scene.setImageResource(R.drawable.ndhu);
                    text.setText("地點:花蓮縣壽豐鄉志學村大學路二段1號974" + "\n\n" + "電話:(03)8630103" + "\n" + "距志學火車站4.3公里" + "\n\n" + "推薦: ☆☆☆☆");
                    break;
                case 1:
                    scene.setImageResource(R.drawable.lishang);
                    text.setText("地點:花蓮縣壽豐鄉理想路一號" + "\n\n" + "電話:(03)8656688轉2239" + "\n" + "距志學火車站4.6公里"+ "\n\n" + "推薦: ☆☆☆☆☆");
                    break;
                case 2:
                    scene.setImageResource(R.drawable.tree);
                    text.setText("地點:花蓮縣壽豐鄉志學村中正路238號" + "\n\n" + "電話:(03)8662382" + "\n" + "距志學火車站0.5公里"+ "\n\n" + "推薦: ☆☆☆");
                    break;
                case 3:
                    scene.setImageResource(R.drawable.bear);
                    text.setText("地點:花蓮縣壽豐鄉鹽寮村山嶺18號" + "\n\n" + "電話:(03)8123999" + "\n" + "距志學火車站6.9公里"+ "\n\n" + "推薦: ☆☆☆☆☆");
                    break;
                case 4:
                    scene.setImageResource(R.drawable.wind);
                    text.setText("地點:花蓮縣吉安鄉干城村干城二街207巷28號" + "\n\n" + "電話:(03)8536697" + "\n" + "距志學火車站4.6公里"+ "\n\n" + "推薦: ☆☆☆☆");
                    break;
                case 5:
                    scene.setImageResource(R.drawable.blue);
                    text.setText("地點:花蓮縣吉安鄉宜昌一街2-2號" + "\n\n" + "電話:(03)8533188" + "\n" + "距志學火車站9.3公里"+ "\n\n" + "推薦: ☆☆☆☆");
                    break;
                case 6:
                    scene.setImageResource(R.drawable.han);
                    text.setText("地點:花蓮縣吉安鄉自強路25號" + "\n\n" + "電話:(03)8539098 " + "\n" + "距志學火車站9.6公里"+ "\n\n" + "推薦: ☆☆☆☆☆");
                    break;
                default:
                    break;
            }
        }
    };

    private Button.OnClickListener web_click = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (position_b){
                case 0:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://lio168.com/about.html"));
                    startActivity(intent);
                    break;
                case 1:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://plcresort.ezhotel.com.tw/1"));
                    startActivity(intent);
                    break;
                case 2:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://murakami.hotel.com.tw/"));
                    startActivity(intent);
                    break;
                case 3:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.farglory-hotel.com.tw/"));
                    startActivity(intent);
                    break;
                case 4:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://sunstar.eminsu.com/"));
                    startActivity(intent);
                    break;
                case 5:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.shiung-hotel.com.tw/"));
                    startActivity(intent);
                    break;
                case 6:
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://hualien.fun-taiwan.com/HouseDetailView.aspx?hid=002-I175"));
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
