package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAplListGridFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAplListGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAplListGridFragment extends Fragment {

    final String TAG = getClass().getSimpleName();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    final static int BACK_KEY = 1;
    private View mView;

    public MyAplListGridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAplListGridFragment.
     */
    public static MyAplListGridFragment newInstance(String param1, String param2) {
        MyAplListGridFragment fragment = new MyAplListGridFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d( TAG, "onCreate() arg=" + savedInstanceState );
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d( TAG, "onCreateView() arg=" + savedInstanceState );
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_apl_list_grid, container, false);

        mView.setFocusableInTouchMode( true ); //use back key.
        mView.requestFocus(); //このViewにフォーカスを移す。(フォーカスが無いとダメ)

        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Log.d( TAG, "onKey() start.keyCode/keyEvent->"+keyCode+" / "+keyEvent );
                if ( keyCode==KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP ) {
                    mListener.onFragmentInteraction( BACK_KEY );

//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.popBackStack();
//
//                    FragmentActivity fragmentActivity = getActivity();
//                    ( (MainActivity)fragmentActivity ).resetDrawer();
//
                }
                return false; //どうぞ続けて処理してください。
//                return true; //これ以上の処理不要
            }
        });

        ArrayList<MyAplListGridData> arrayList = getData();
        MyAplListGridAdapter myAplListGridAdapter = new MyAplListGridAdapter(  getContext(), arrayList );

        GridView gridView = mView.findViewById( R.id.grid_apllist );
        gridView.setAdapter( myAplListGridAdapter );

        //set listener to gridview.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d( TAG, "GridView.OnItemClick() position/id->" + position + " / " + id );
                launchTappedApplication( parent, view, position, id );
            }
        });

        return mView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d( TAG, "onOptionsItemSelected()" );
        switch ( item.getItemId() ) {
            case R.id.homeAsUp :
                Log.d( TAG, "R.id.homeAsUp" );
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d( TAG, "onSaveInstanceState()" );
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d( TAG, "onViewStateRestored()" );
        super.onViewStateRestored(savedInstanceState);
    }

    private void launchTappedApplication(AdapterView<?> parent, View view, int position, long id ) {

        MyAplListGridData item = (MyAplListGridData) parent.getItemAtPosition( position );

        PackageManager packageManager = getContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage( item.getPackageName() );
        if ( intent==null || intent.resolveActivity( packageManager )==null ) { //resolveActivity() : 該当するアプリが１つ以上あるか確認（無＝null）
            Log.d( TAG, "package not found. [ " + item.getPackageName() + " ]" );
            return;
        }
        //execute application
        startActivity( intent );
    }

    private ArrayList<MyAplListGridData> getData() {

        String[] strMyApl = { "com.example.user.myappl09", "com.example.user.myproject2.main.deb",
                "com.example.user.wifioperation", "com.example.user.myokusuri.main.debug" };

        ArrayList<MyAplListGridData> arrayList = new ArrayList<>();

        //get label, icon (drawable)
        PackageManager packageManager = getContext().getPackageManager();
        PackageInfo packageInfo = null;
        for (String aStrMyApl : strMyApl) {

            try {
                packageInfo = packageManager.getPackageInfo(aStrMyApl, PackageManager.GET_ACTIVITIES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageInfo == null) {
                Log.d(TAG, "packageInfo is null.");
                return null;
            }

            ActivityInfo[] activityInfos = packageInfo.activities;
            MyAplListGridData gridData = new MyAplListGridData();

            TextView textView = new TextView(getContext());
            textView.setText(String.valueOf(activityInfos[0].loadLabel(packageManager)));
            gridData.setTextView(textView);

            Drawable drawable = activityInfos[0].loadIcon(packageManager);
            drawable.setBounds(0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth()); //大きさを指定しないと表示しない
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(drawable);
            gridData.setImageView(imageView);

            gridData.setPackageName(aStrMyApl);

            arrayList.add(gridData);

        } //for(i)

        return arrayList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction( int id );
    }
}
