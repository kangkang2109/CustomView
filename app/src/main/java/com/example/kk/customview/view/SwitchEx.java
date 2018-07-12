package com.example.kk.customview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.example.kk.customview.R;

public class SwitchEx extends CheckBox {

    private static Bitmap bottom;
    private static Bitmap frame;
    private static Bitmap framePressed;
    private static Bitmap mask;
    private static Bitmap btnPressed;
    private static Bitmap btn;
    private Bitmap btnOffBitmap;
    private Bitmap btnOnBitmap;

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

    private static int frameWidth;
    private static int frameHeight;
    private static int bottomWidth;
    private static int bottomHeight;
    private int btnOffPos;
    private int btnOnPos;

    private Paint mPaint;
    private Canvas mCanvas;


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

        mCanvas = new Canvas();
        btnOffBitmap = createBitmap(bottomWidth / 2 - btnOffPos);



    }


    private Bitmap createBitmap(int pos){
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(frameWidth, frameHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(frame, 0, 0, mPaint);
    }
}
