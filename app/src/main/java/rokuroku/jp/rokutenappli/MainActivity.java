package rokuroku.jp.rokutenappli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListener(); //リスナー登録
    }
    private void showNekoShokai() {
        //うちの猫紹介fragmentでアピール
    }

    private void setListener() {
        //猫紹介のLinearLayoutのリスナー登録
        //View取得
//        TextView nekoShokai = findViewById( R.id.text_neko_shokai );
        RelativeLayout relaiveNekoShokai = findViewById( R.id.main_head );
        //リスナー登録
//        nekoShokai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d( TAG, "textView click Neko Shokai !"  );
//                //うちの猫紹介fragmentを表示する。
//                showNekoShokai();
//            }
//        });
        relaiveNekoShokai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d( TAG, "relativeLayout click Neko Shokai !"  );
                //うちの猫紹介fragmentを表示する。
                showNekoShokai();
            }
        });
    }
}
