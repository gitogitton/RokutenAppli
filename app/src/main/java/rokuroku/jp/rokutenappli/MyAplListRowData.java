package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAplListRowData {

    private Context mContext;
    private ArrayList<TextView> mTextViews =new ArrayList<>();

    final static int ROW_DATA_NUM = 3;  //1行のデータ（textview）の数・・・なんだかかっこ悪いような・・・

    //constructor
    public MyAplListRowData( Context context ) {
        mContext = context;
        for ( int i=0; i<ROW_DATA_NUM; i++ ) {
            TextView textView = new TextView( mContext );
            mTextViews.add( textView );
        }
    }

    //set, get
    public int getMaxNumOfData() {
        return ROW_DATA_NUM;
    }
    public void setData( int position, TextView obj ) {
        mTextViews.set( position, obj ); //replace obj
    }
    public TextView getData( int position ) {
        return  mTextViews.get( position );
    }
}

