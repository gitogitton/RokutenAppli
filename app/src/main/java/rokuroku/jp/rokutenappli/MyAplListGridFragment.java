package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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
    // TODO: Rename and change types and number of parameters
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
        mView = inflater.inflate(R.layout.fragment_my_apl_list_grid, container, false);

        ArrayList<MyAplListGridData> arrayList = getData();

        MyAplListGridAdapter myAplListGridAdapter = new MyAplListGridAdapter(  getContext(), arrayList );

        GridView gridView = mView.findViewById( R.id.grid_apllist );
        gridView.setAdapter( myAplListGridAdapter );

        return mView;
    }

    private ArrayList<MyAplListGridData> getData() {

        String[] strMyApl = { "com.example.user.myappl09", "com.example.user.myproject2.main.deb",
                "com.example.user.wifioperation", "com.example.user.myokusuri.main.debug" };

        ArrayList<MyAplListGridData> arrayList = new ArrayList<>();

        //get label, icon (drawable)
        PackageManager packageManager = getContext().getPackageManager();
        PackageInfo packageInfo = null;
        for ( int i=0; i<strMyApl.length; i++ ) {

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
            MyAplListGridData gridData = new MyAplListGridData();

            TextView textView = new TextView( getContext() );
            textView.setText( String.valueOf( activityInfos[0].loadLabel( packageManager ) ) );
            gridData.setTextView( textView );

            Drawable drawable = activityInfos[0].loadIcon(packageManager);
            drawable.setBounds( 0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth() ); //大きさを指定しないと表示しない
            ImageView imageView = new ImageView( getContext() );
            imageView.setImageDrawable( drawable );
            gridData.setImageView( imageView );

            arrayList.add( gridData );

        } //for(i)

        return arrayList;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed( int id ) {
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
        // TODO: Update argument type and name
        void onFragmentInteraction( int id );
    }
}
