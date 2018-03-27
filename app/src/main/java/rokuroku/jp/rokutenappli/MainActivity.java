package rokuroku.jp.rokutenappli;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<TextView> mArrayList = new ArrayList<>();
    private int mPagerPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNaviDrawer();
        initMenu();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.menu01_code :
                Log.d( TAG, "menu->menu01_code" );
                return true;
            case R.id.menu02_info :
                Log.d( TAG, "menu->menu02_info" );
                return true;
            case R.id.menu03_appl :
                Log.d( TAG, "menu->menu03_appl" );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showNekoShokai() {
        //うちの猫紹介fragmentでアピール
    }

    private void initNaviDrawer() {

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navi_drawer_open, R.string.navi_drawer_close );

        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d( TAG, "navigationView " );
                return false;
            }
        });
    }

    private void initMenu() {

    }

    private void init() {
        //View取得
        //猫紹介
        RelativeLayout relaiveNekoShokai = findViewById(R.id.main_head);
        //体重グラフ｜体脂肪グラフ　エリア
        LinearLayout linearGraphTaiju = findViewById(R.id.main_body02);
        TextView textTaijuGraph = linearGraphTaiju.findViewById(R.id.text_taijyu); //体重グラフ
        TextView textTaisiboGraph = linearGraphTaiju.findViewById(R.id.text_taisiboritu); //体脂肪グラフ
        //体脂肪変換ボタン
        LinearLayout linearHenkanShibo = findViewById(R.id.main_body03);
        //「もっと見ます？」ボタン
        RelativeLayout relativeMotto = findViewById( R.id.main_body05 );

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
        relativeMotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "relativeMotto click Motto !");
            }
        });

        //ViewPgerの設定
        setViewPager();

    }//init()

    private void setViewPager() {
        ViewPager viewPager = findViewById( R.id.viewPager );
        //set adapter for viewPager
        MyViewPagerAdapter myPagerAdapter = new MyViewPagerAdapter( this );
        viewPager.setAdapter( myPagerAdapter );
        //set adapter of viewPager for SlidingTabStrip
        PagerSlidingTabStrip tabs = findViewById( R.id.tabs );
        tabs.setViewPager( viewPager );
        //get tab resource
        int tabCount = tabs.getTabCount();
        LinearLayout linearLayout = tabs.getTabsContainer();
        for ( int i=0; i<tabCount; i++ ) {
            TextView textView = (TextView)linearLayout.getChildAt( i );
            if ( i==0 ) {
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds( R.drawable.icon_red, 0, 0, 0 );
            }
            else {
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds( R.drawable.icon_blue, 0, 0, 0 );
            }
            mArrayList.add( textView );
        }
        //set listener
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d( TAG, "TAB change Page. position / offset / offsetPixels->" + position + " / " + positionOffset + " / " + positionOffsetPixels );
            }
            @Override
            public void onPageSelected(int position) {
                Log.d( TAG, "onPageSelected() : Select Position->" + position + " Prev.->" + mPagerPos );
                mArrayList.get( position ).setCompoundDrawablesRelativeWithIntrinsicBounds( R.drawable.icon_red, 0, 0, 0 );
                mArrayList.get( mPagerPos ).setCompoundDrawablesRelativeWithIntrinsicBounds( R.drawable.icon_blue, 0, 0, 0 );
                mPagerPos = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d( TAG, "TAB Page scroll status. state->" + state );
            }
        });
    }

}
