package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAplListGridAdapter extends ArrayAdapter<MyAplListGridData> {

    private LayoutInflater mInflater;
    class chidViews {
        ImageView imageView;
        TextView textView;
    };
    private chidViews mChildViews;
    public MyAplListGridAdapter(@NonNull Context context, @NonNull List<MyAplListGridData> objects) {
        super(context, 0, objects);
        mInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public MyAplListGridData getItem(int position) {
        return super.getItem( position );
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId( position );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if ( convertView == null ) {
            convertView = mInflater.inflate( R.layout.grid_item, parent, false );
            //childView 取得
            mChildViews = new chidViews();
            mChildViews.imageView = convertView.findViewById( R.id.image_apl );
            mChildViews.textView = convertView.findViewById( R.id.text_apl_name );
        }

        //指定行のデータ
        MyAplListGridData item = getItem( position );

        //データセット
        mChildViews.imageView.setImageDrawable( item.getImageView().getDrawable() );
        mChildViews.textView.setText( item.getTextView().getText() ); ;

        return convertView;
    }
}
