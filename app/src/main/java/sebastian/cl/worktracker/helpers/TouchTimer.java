package sebastian.cl.worktracker.helpers;

import android.view.MotionEvent;
import android.view.View;

public abstract class TouchTimer  implements View.OnTouchListener{
    private long touchStart = 01;
    private long touchEnd = 0;

    @Override
    public boolean onTouch (View view, MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.touchStart = System.currentTimeMillis();
                return true;

            case MotionEvent.ACTION_UP:
                this.touchEnd = System.currentTimeMillis();
                long touchTime = this.touchEnd - this.touchStart;
                onTouchEnded(touchTime, view);
                return true;

            case MotionEvent.ACTION_MOVE:
                return true;

                default:
                    return false;
        }
    }

    protected abstract void onTouchEnded(long TouchInMillis, View view);
}
