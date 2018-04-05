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
        }

        //view 取得
        ImageView imageView = convertView.findViewById( R.id.image_apl );
        TextView textView = convertView.findViewById( R.id.text_apl_name );

        //指定行のデータ
        MyAplListGridData item = getItem( position );

        //データセット
        imageView.setImageDrawable( item.getImageView().getDrawable() );
        textView.setText( item.getTextView().getText() ); ;

        return convertView;
    }
}
