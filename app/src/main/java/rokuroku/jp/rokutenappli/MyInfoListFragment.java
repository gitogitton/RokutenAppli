package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyInfoListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyInfoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyInfoListFragment extends Fragment {

    final String TAG = getClass().getSimpleName();
    final static int BACK_KEY = 1;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;

    public MyInfoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyInfoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyInfoListFragment newInstance(String param1, String param2) {
        MyInfoListFragment fragment = new MyInfoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_info_list, container, false);

        mView.setFocusableInTouchMode( true ); //use back key.
        mView.requestFocus(); //このViewにフォーカスを移す。(フォーカスが無いとダメ)
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Log.d( TAG, "onKey() start.keyCode/keyEvent->"+keyCode+" / "+keyEvent );
                if ( keyCode==KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP ) {
                    mListener.onFragmentInteraction( BACK_KEY );
                }
                return false;
            }
        });

        //データ表示
        setData();

        return mView;
    }

    private void setData() {
        ListView listView = mView.findViewById( R.id.info_list );
        ArrayList<MyInfoListRowData> arrayList = getData();
        MyInfoListAdapter myInfoListAdapter = new MyInfoListAdapter( getContext(), R.layout.info_list_row, arrayList );
        listView.setAdapter( myInfoListAdapter );
    }

    private ArrayList<MyInfoListRowData> getData() {
        TextView textView = new TextView( getContext() );
        textView.setText( "2018/04/10\nこの画面を追加しました。" );

        MyInfoListRowData myInfoListRowData = new MyInfoListRowData();
        myInfoListRowData.setTextView( textView );

        ArrayList<MyInfoListRowData> arrayList = new ArrayList<>();
        arrayList.add( myInfoListRowData );

        return arrayList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu( false ); //オプションメニューを使用しない事を宣言
    }

    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction( id );
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int id);
    }
}
