package rokuroku.jp.rokutenappli;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAplListGridData {

    private ImageView mImageView;
    private TextView mTextView;
    private String mPackageName;

    //getter, setter
    public String getPackageName() {
        return this.mPackageName;
    }
    public void setPackageName( String packageName ) {
        this.mPackageName = packageName;
    }
    public ImageView getImageView() {
        return mImageView;
    }
    public void setImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }
    public void setImageDrawer(Drawable drawable) {
        this.mImageView.setImageDrawable( drawable );
    }
    public TextView getTextView() {
        return mTextView;
    }

    public void setTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }
}
