package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAplListAdapter extends ArrayAdapter<MyAplListRowData> {

    private final String TAG = getClass().getSimpleName();
    private LayoutInflater mInflater;
    private Context mContext;

    public MyAplListAdapter(@NonNull Context context, @NonNull List<MyAplListRowData> objects) {
        super(context, 0, objects);
        mContext = context;
        mInflater = LayoutInflater.from( mContext );
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //再利用可能なViewがない場合は生成する。
        if ( convertView==null ) {
            convertView = mInflater.inflate( R.layout.my_apl_list_row, parent, false );
        }

        //View 取得
        TextView textMyApl_01 = convertView.findViewById( R.id.text_myapl_01 );
        TextView textMyApl_02 = convertView.findViewById( R.id.text_myapl_02 );
        TextView textMyApl_03 = convertView.findViewById( R.id.text_myapl_03 );

        //position で指定された行を取得
        MyAplListRowData myAplListRowData = getItem( position );

        //
        //各 View へ position で指定されたデータをセット
        //
        //テキスト
        textMyApl_01.setText( myAplListRowData.getData(0).getText() );;
        textMyApl_02.setText( myAplListRowData.getData(1).getText() );
        textMyApl_03.setText( myAplListRowData.getData(2).getText() );
        //アイコン (left, top, right, bottom)
        Drawable[] textDrawable_01 = myAplListRowData.getData(0).getCompoundDrawables();
        Drawable[] textDrawable_02 = myAplListRowData.getData(1).getCompoundDrawables();
        Drawable[] textDrawable_03 = myAplListRowData.getData(2).getCompoundDrawables();
        textMyApl_01.setCompoundDrawables( textDrawable_01[0], textDrawable_01[1], textDrawable_01[2], textDrawable_01[3]);
        textMyApl_02.setCompoundDrawables( textDrawable_02[0], textDrawable_02[1], textDrawable_02[2], textDrawable_02[3]);
        textMyApl_03.setCompoundDrawables( textDrawable_03[0], textDrawable_03[1], textDrawable_03[2], textDrawable_03[3]);

        return convertView;
    }
}
