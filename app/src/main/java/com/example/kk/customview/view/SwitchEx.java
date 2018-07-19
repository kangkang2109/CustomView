package com.example.kk.customview.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.example.kk.customview.R;

public class SwitchEx extends android.support.v7.widget.AppCompatCheckBox {

    private static Bitmap bottom;
    private static Bitmap frame;
    private static Bitmap framePressed;
    private static Bitmap mask;
    private static Bitmap btnPressed;
    private static Bitmap btn;
    private Bitmap btnOffBitmap;
    private Bitmap btnOnBitmap;
    private Bitmap frameBitmap;
    private Bitmap btnBitmap;

    private static final int[] mDrawableIds = {
            R.drawable.switch_ex_bottom,
            R.drawable.switch_ex_frame,
            R.drawable.switch_ex_frame_pressed,
            R.drawable.switch_ex_mask,
            R.drawable.switch_ex_pressed,
            R.drawable.switch_ex_unpressed,
    };
    private static final int BOTTOM = 0;
    private static final int FRAME = 1;
    private static final int FRAME_PRESSED = 2;
    private static final int MASK = 3;
    private static final int BTN_PRESSED = 4;
    private static final int BTN = 5;

    private final float VELOCITY = 350;
    private final float ANIMATION_FRAME_DURATION = 1000 / 60;
    private float mVelocity;
    private float mAnimatedVelocity;


    private static int frameWidth;
    private static int frameHeight;
    private static int bottomWidth;
    private static int bottomHeight;
    private int btnOffPos;
    private int btnOnPos;
    private int currPos;
    private float firstX;
    private int offsetX;

    private Paint mPaint;
    private Canvas mCanvas;
    /**
     *  关于PorterDuff.Mode.SRC_IN注意事项
     *  １. 硬件加速影响
     *   (官网说明http://developer.android.com/guide/topics/graphics/hardware-accel.html)
     *   this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
     *　 2. 注意图片位置,大小,背景
     *
     */
    PorterDuffXfermode xfermode;
    private boolean mAnimating;
    private int mAnimationPosition;


    public SwitchEx(Context context) {
        this(context, null);
    }

    public SwitchEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        mPaint = new Paint();
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView(Context context) {
        bottom = ((BitmapDrawable)context.getResources().getDrawable(mDrawableIds[BOTTOM], null)).getBitmap();
        frame = ((BitmapDrawable)context.getResources().getDrawable(mDrawableIds[FRAME], null)).getBitmap();
        framePressed = ((BitmapDrawable)context.getResources().getDrawable(mDrawableIds[FRAME_PRESSED], null)).getBitmap();
        mask = ((BitmapDrawable)context.getResources().getDrawable(mDrawableIds[MASK], null)).getBitmap();
        btnPressed = ((BitmapDrawable)context.getResources().getDrawable(mDrawableIds[BTN_PRESSED], null)).getBitmap();
        btn = ((BitmapDrawable)context.getResources().getDrawable(mDrawableIds[BTN], null)).getBitmap();

        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
        bottomWidth = bottom.getWidth();
        bottomHeight = bottom.getHeight();
        btnOffPos = frameWidth - bottomWidth / 2;
        btnOnPos = bottomWidth / 2;
        currPos = btnOffPos;

        final float density = getResources().getDisplayMetrics().density;
        mVelocity = (int) (VELOCITY * density + 0.5f);

        mCanvas = new Canvas();
        btnBitmap = btn;
        frameBitmap = frame;

    }

    public int getRealPos(int pos) {
        return pos - bottomWidth / 2;
    }

    private Bitmap createBitmap(int pos){
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(frameWidth + 100, frameHeight + 100);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN :
//                btnBitmap = btnPressed;
//                frameBitmap = framePressed;
//                firstX = x;
//                break;
//            case MotionEvent.ACTION_MOVE :
//                offsetX = (int) (x - firstX);
//                startAnimation();
//
//                break;
//            case MotionEvent.ACTION_UP :
//            case MotionEvent.ACTION_CANCEL :
//                btnBitmap = btn;
//                frameBitmap = frame;
//                break;
//
//        }
//        invalidate();
//        return true;
//    }

    private void startAnimation() {
        mAnimatedVelocity = (offsetX > 0) ? mVelocity : -mVelocity;
        mAnimating = true;
        mAnimationPosition = btnOffPos;
        doAnimation();
    }

    private void doAnimation() {
        mAnimationPosition += mAnimatedVelocity * ANIMATION_FRAME_DURATION / 1000;
        if (mAnimationPosition <= btnOffPos) {
            //stopAnimation();
            mAnimationPosition = btnOffPos;
        } else if (mAnimationPosition >= btnOnPos) {
            //stopAnimation();
            mAnimationPosition = btnOnPos;
        }
        currPos = getRealPos(mAnimationPosition);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save(); //保留画布的状态
        canvas.drawBitmap(mask, 0, 0, mPaint);
        mPaint.setXfermode(xfermode);
        canvas.drawBitmap(bottom, getRealPos(currPos), 0, mPaint);
        mPaint.setXfermode(null);
        canvas.drawBitmap(frameBitmap, 0, 0, mPaint);
        canvas.drawBitmap(btnBitmap, getRealPos(currPos), 0, mPaint);
        canvas.restore(); //恢复画布的状态
    }
}
