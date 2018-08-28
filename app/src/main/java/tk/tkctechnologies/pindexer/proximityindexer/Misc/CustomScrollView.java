package tk.tkctechnologies.pindexer.proximityindexer.Misc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by codename-tkc on 02/01/2018.
 */

public class CustomScrollView extends ScrollView {
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        if(l)
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
