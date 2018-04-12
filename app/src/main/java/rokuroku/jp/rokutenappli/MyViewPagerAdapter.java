package rokuroku.jp.rokutenappli;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by user on 2018/03/23.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    class childViews {
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
    };
    private childViews mChildViews;
    private int mResourceIds[] = { R.mipmap.bijiri, R.mipmap.gohanwomatu, R.mipmap.kaseifuwamita,
            R.mipmap.keikaisuru, R.mipmap.nekopoi, R.mipmap.nemureru,
            R.mipmap.ryakudatumofu, R.mipmap.shogekinosinjitu, R.mipmap.yaseiwowasureta,
            R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round  };

//    private LayoutInflater mLayoutInflater;
    private final int IMAGE_NUM_IN_PAGE = 3;

    MyViewPagerAdapter( Context context ) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mResourceIds.length / IMAGE_NUM_IN_PAGE;
    }

    //ページを構成するViewの判定
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ( view == object );
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View itemView;
        if (mLayoutInflater != null) {
            itemView = mLayoutInflater.inflate( R.layout.layout_swiped, container, false );
            mChildViews = new childViews();
            mChildViews.imageView1 = itemView.findViewById( R.id.imageView1 );
            mChildViews.imageView2 = itemView.findViewById( R.id.imageView2 );
            mChildViews.imageView3 = itemView.findViewById( R.id.imageView3 );

        } else {
            throw new NullPointerException("layoutInflater required Non Null.");
        }

        if ( itemView == null ) {
            throw new NullPointerException("itemView required Non Null.");
        }

        int startPos = position * IMAGE_NUM_IN_PAGE;
        if (mChildViews.imageView1 != null) {
            mChildViews.imageView1.setImageResource( mResourceIds[ startPos ] );
        }
        if (mChildViews.imageView2 != null) {
            mChildViews.imageView2.setImageResource( mResourceIds[ startPos+1 ] );
        }
        if (mChildViews.imageView3 != null) {
            mChildViews.imageView3.setImageResource( mResourceIds[ startPos+2 ] );
        }

        container.addView( itemView );

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (LinearLayout)object );
    }

    @Override
    public int getItemPosition( @NonNull Object object ) {
        return super.getItemPosition(object);
    }

}
