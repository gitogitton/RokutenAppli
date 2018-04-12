package rokuroku.jp.rokutenappli;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyDescImageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyDescImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyDescImageFragment extends Fragment {
    private final  String TAG = getClass().getSimpleName();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "drawable";
    static final int BACK_KEY = 1;

    private MyDrawableData mArgument;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    private View mView;

    public MyDescImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyDescImageFragment.
     */
    public static MyDescImageFragment newInstance(String param1, String param2) {
        MyDescImageFragment fragment = new MyDescImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArgument = (MyDrawableData) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_desc_image, container, false);
        mView.setFocusableInTouchMode( true ); //use back key.
        mView.requestFocus(); //このViewにフォーカスを移す。(フォーカスが無いとダメ)
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
                Log.d( TAG, "onKey() start.keyCode/keyEvent->"+keyCode+" / "+keyEvent );
                if ( keyCode==KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP ) {
                    mListener.onFragmentInteraction( BACK_KEY );
                }
                return false;
            }
        });
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageView imageView = view.findViewById( R.id.imageView4 );
        imageView.setImageDrawable( mArgument.getDrawable() );

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView img = new ImageView( mContext );
                Bitmap bitmap = ( (BitmapDrawable)imageView.getDrawable() ).getBitmap();
                img.setImageBitmap( bitmap );

                // ディスプレイの幅を取得する（API 13以上）
                Display display =  ( (Activity)mContext ).getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize( size );
                int width = size.x;

                float factor =  width / bitmap.getWidth();
                Log.d("Tap Image", "factor の値 = "+ factor);

                img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // ダイアログを作成する
                Dialog dialog = new Dialog( mContext );
                // タイトルを非表示にする
                dialog.getWindow().requestFeature( Window.FEATURE_NO_TITLE );

                dialog.setContentView( img );

                dialog.getWindow().setLayout( (int)( bitmap.getWidth()*factor ), (int)( bitmap.getHeight()*factor ) );
                dialog.getWindow().setLayout( (int)( bitmap.getWidth()*factor ), (int)( bitmap.getHeight()*factor ) );
                // ダイアログを表示する
                dialog.show();
            }
        });
    }

    public void onButtonPressed(int id ) {
        if (mListener != null) {
            mListener.onFragmentInteraction( id );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
        void onFragmentInteraction( int id );
    }
}
