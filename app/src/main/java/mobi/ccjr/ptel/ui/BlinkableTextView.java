package mobi.ccjr.ptel.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class BlinkableTextView
        extends TextView {

    public BlinkableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BlinkableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BlinkableTextView(Context context) {
        super(context);
    }

    protected void blink() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(250);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(4);
        startAnimation(anim);
    }

}
