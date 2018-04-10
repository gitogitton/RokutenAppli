package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class MyInfoListAdapter extends ArrayAdapter<MyInfoListRowData> {
    private Context mContext;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    public MyInfoListAdapter(@NonNull Context context, int resource, @NonNull List<MyInfoListRowData> objects) {
        super(context, resource, objects);
        mContext = context;
        mInflater = LayoutInflater.from( mContext );
        mLayoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if ( convertView==null ) {
            convertView = mInflater.inflate( mLayoutResource, parent, false );
        }
        MyInfoListRowData rowData = getItem( position );
        TextView textView = convertView.findViewById( R.id.text_info_list_row );
        textView.setText( rowData.getTextView().getText() );
        return convertView;
    }
}
