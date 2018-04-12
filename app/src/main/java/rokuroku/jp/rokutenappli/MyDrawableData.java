package rokuroku.jp.rokutenappli;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class MyDrawableData implements Serializable {
    private Drawable mDrawable;

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }
}
