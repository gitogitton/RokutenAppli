package rokuroku.jp.rokutenappli;

import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private ViewPager mViewPager;

    private int mPagerPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager();
        setListener(); //リスナー登録
    }

    private void showNekoShokai() {
        //うちの猫紹介fragmentでアピール
    }

    private void setViewPager() {

        mViewPager = findViewById( R.id.viewPager );
        MyViewPagerAdapter myPagerAdapter = new MyViewPagerAdapter( this );
        mViewPager.setAdapter( myPagerAdapter );

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById( R.id.tabs );
        tabs.setViewPager( mViewPager );

        int tabCount = tabs.getTabCount();
        LinearLayout linearLayout = tabs.getTabsContainer();
        ArrayList<TextView> arrayList = new ArrayList<>();
        for ( int i=0; i<tabCount; i++ ) {
            TextView textView = (TextView)linearLayout.getChildAt( i );
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds( R.drawable.icon_blue, 0, 0, 0 );
        }

//        tabs.setIndicatorColor( Color.RED );
//        tabs.setTextColor( Color.RED );


        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d( TAG, "TAB change Page. position / offset / offsetPixels->" + position + " / " + positionOffset + " / " + positionOffsetPixels );
            }

            @Override
            public void onPageSelected(int position) {
                Log.d( TAG, "TAB Select Page. position->" + position );
                switch( position ) {
                    case 0:
                        Log.d( TAG, "Select Position->" + position + " Prev.->" + mPagerPos );
                        break;
                    case 1:
                        Log.d( TAG, "Select Position->" + position + " Prev.->" + mPagerPos );
                        break;
                    case 2:
                        Log.d( TAG, "Select Position->" + position + " Prev.->" + mPagerPos );
                        break;
                    case 3:
                        Log.d( TAG, "Select Position->" + position + " Prev.->" + mPagerPos );
                        break;
                    case 4:
                        Log.d( TAG, "Select Position->" + position + " Prev.->" + mPagerPos );
                        break;
                }
                mPagerPos = position + 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d( TAG, "TAB Page scroll status. state->" + state );
            }
        });

    }

    private void setListener() {
        //猫紹介のLinearLayoutのリスナー登録
        //View取得
        RelativeLayout relaiveNekoShokai = findViewById(R.id.main_head);
        //体重グラフ｜体脂肪グラフ　エリア
        LinearLayout linearGraphTaiju = findViewById(R.id.main_body02);
        TextView textTaijuGraph = linearGraphTaiju.findViewById(R.id.text_taijyu); //体重グラフ
        TextView textTaisiboGraph = linearGraphTaiju.findViewById(R.id.text_taisiboritu); //体脂肪グラフ
        //体脂肪変換ボタン
        LinearLayout linearHenkanShibo = findViewById(R.id.main_body03);
        //広告

        //リスナー登録
        relaiveNekoShokai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "relativeLayout click Neko Shokai !");
                //うちの猫紹介fragmentを表示する。
                showNekoShokai();
            }
        });
        textTaijuGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "textTaijuGraph click Neko Shokai !");
            }
        });
        textTaisiboGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "textTaisiboGraph click Neko Shokai !");
            }
        });
        linearHenkanShibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "linearHenkanShibo click Neko Shokai !");
            }
        });


    }//setListener()
}

//        RadioButton radioButton1 = scrollIconCm.findViewById( R.id.radioButton );
//        RadioButton radioButton2 = scrollIconCm.findViewById( R.id.radioButton2 );
//        RadioButton radioButton3 = scrollIconCm.findViewById( R.id.radioButton3 );
//        RadioButton radioButton4 = scrollIconCm.findViewById( R.id.radioButton4 );
//        RadioButton radioButton5 = scrollIconCm.findViewById( R.id.radioButton5 );
//        mRadioButtonList.add( radioButton1 );
//        mRadioButtonList.add( radioButton2 );
//        mRadioButtonList.add( radioButton3 );
//        mRadioButtonList.add( radioButton4 );
//        mRadioButtonList.add( radioButton5 );



//        scrollIconCm.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d(TAG, "scrollIconCm.setOnTouchListener  Neko Shokai ! View->" + getApplicationContext().getResources().getResourceEntryName( view.getId()) );
//                Log.d(TAG, "scrollIconCm.setOnTouchListener  Neko Shokai ! MotionEvent->" + motionEvent );
//                //performClick(); //クリックイベントを起こす。
//                return false;
//            }
//        });

//イベントの起源！！！？？
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "dispatchTouchEvent() start. [MotionEvent : " + ev.toString() );
//        return super.dispatchTouchEvent(ev);
//    }

