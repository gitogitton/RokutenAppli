package rokuroku.jp.rokutenappli;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAplListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAplListFragment extends Fragment {

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int id);
    }
    private OnFragmentInteractionListener mListener = null;
    static final int KEY_CODE_DOWN = 1;

    private final String TAG = getClass().getSimpleName();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mView;

    public MyAplListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAplListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAplListFragment newInstance(String param1, String param2) {
        MyAplListFragment fragment = new MyAplListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.d( TAG, "onCreate() start." );
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //Log.d( TAG, "onActivityCreated() start." );
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu( false ); //オプションメニューを使用しない事を宣言
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.d( TAG, "onCreateView() start." );
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_apl_list, container, false);

        mView.setFocusableInTouchMode( true ); //use back key.
        mView.requestFocus(); //このViewにフォーカスを移す。(フォーカスが無いとダメ)

        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Log.d( TAG, "onKey() start.keyCode/keyEvent->"+keyCode+" / "+keyEvent );
                if ( keyCode==KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP ) {
                    mListener.onFragmentInteraction( KEY_CODE_DOWN );
                }
                return false;
            }
        });

        //データ表示
        setData();

        return mView;
    }

    private void setData() {
        ListView listView = mView.findViewById( R.id.list_appl );

        ArrayList<MyAplListRowData> aplListLineDataArrayList;
        aplListLineDataArrayList = getData();

        MyAplListAdapter myAplListAdapter = new MyAplListAdapter( getContext(), aplListLineDataArrayList );
        listView.setAdapter( myAplListAdapter );
    }

    private ArrayList<MyAplListRowData> getData() {

        String[] strMyApl = { "com.example.user.myappl09", "com.example.user.myproject2.main.deb",
                "com.example.user.wifioperation", "com.example.user.myokusuri.main.debug" };

        ArrayList<MyAplListRowData> arrayList = new ArrayList<>();

        //get label, icon (drawable)
        PackageManager packageManager = getContext().getPackageManager();
        PackageInfo packageInfo = null;
        MyAplListRowData rowData = null;
        int maxDataNumPerRow = MyAplListRowData.ROW_DATA_NUM;
        for ( int i=0; i<strMyApl.length; i++ ) {
            if( ( i % maxDataNumPerRow )==0 ) { //maxDataNumPerRow おきに書き込む（一行あたりのデータTextViewの数）
                rowData = new MyAplListRowData( getContext() );
            }
            try {
                packageInfo = packageManager.getPackageInfo( strMyApl[i], PackageManager.GET_ACTIVITIES );
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if ( packageInfo==null ) {
                Log.d( TAG, "packageInfo is null." );
                return null;
            }
            ActivityInfo[] activityInfos = packageInfo.activities;
            Drawable drawable = activityInfos[0].loadIcon(packageManager);
            drawable.setBounds( 0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth() ); //大きさを指定しないと表示しない

            TextView textView = new TextView( getContext() );
            textView.setCompoundDrawables( null, drawable, null, null );
            textView.setText( String.valueOf( activityInfos[0].loadLabel( packageManager ) ) );

            rowData.setData( i % maxDataNumPerRow, textView );

            if ( ( i % maxDataNumPerRow )==2 || i==strMyApl.length-1 ) { //maxDataNumPerRow 分たまってから書く
                arrayList.add( rowData );
            }
        } //for(i)
        return arrayList;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d( TAG, "onCreateOptionsMenu() start." );
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        Log.d( TAG, "onPrepareOptionsMenu() start." );
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d( TAG, "onOptionsItemSelected() item->"+item );
        switch ( item.getItemId() ) {
            case android.R.id.home :
                Log.d( TAG, "homeAsUp" );
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onAttach(Context context) {
        //Log.d( TAG, "onAttach() start." );
        super.onAttach(context);
        if ( context instanceof OnFragmentInteractionListener ) {
            mListener = (OnFragmentInteractionListener) context; //リスナー登録
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    //lifecycle method
    @Override
    public void onStart() {
        Log.d( TAG, "onStart() start." );
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d( TAG, "onResume() start." );
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d( TAG, "onPause() start." );
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d( TAG, "onStop() start." );
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d( TAG, "onDestroyView() start." );
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d( TAG, "onDestroy() start." );
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d( TAG, "onDetach() start." );
        super.onDetach();
        mListener = null;
    }
}
