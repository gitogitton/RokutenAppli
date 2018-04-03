package rokuroku.jp.rokutenappli;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


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

    private Activity mActivity;


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

        setHasOptionsMenu( false ); //オプションメニューを使用する事を宣言
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.d( TAG, "onCreateView() start." );
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_apl_list, container, false);

        view.setFocusableInTouchMode( true ); //use back key.
        view.requestFocus(); //このViewにフォーカスを移す。(フォーカスが無いとダメ)

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Log.d( TAG, "onKey() start.keyCode/keyEvent->"+keyCode+" / "+keyEvent );
                if ( keyCode==KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP ) {
                    mListener.onFragmentInteraction( KEY_CODE_DOWN );
                }
                return false;
            }
        });
        return view;
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
}
