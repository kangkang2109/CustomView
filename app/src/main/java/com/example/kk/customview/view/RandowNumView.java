package com.example.kk.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.kk.customview.R;

/**
 * 1. 画出基本轮廓
 * 2. 添加wrap_content,padding
 * 3. 调整大小
 */
public class RandowNumView extends View {

    private int mSize;
    private String mContent;
    private int mColor;

    private Paint mPaint;
    private Rect mRect;

    public RandowNumView(Context context) {
        this(context, null);
    }

    /**
     * 直接在XML中定义>style定义>由defStyleAttr定义的值>defStyleRes指定的默认值、直接在Theme中指定的值
     */
    public RandowNumView(Context context, @Nullable AttributeSet attrs) {
        //默认属性
        this(context, attrs, R.attr.DefaultRandowNumViewAttr);
    }

    public RandowNumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RandowNumView, defStyleAttr, R.style.DefaultRandowNumViewStyle);
        mContent = ta.getString(R.styleable.RandowNumView_num);
        mColor = ta.getColor(R.styleable.RandowNumView_color, 0);
        mSize = ta.getDimensionPixelSize(R.styleable.RandowNumView_numSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));   //默认大小为16sp

        mPaint = new Paint();
        mPaint.setTextSize(mSize);
        mPaint.setColor(mColor);
        mRect = new Rect();
        mRect.bottom += getPaddingBottom();
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        ta.recycle(); // 避免内存泄漏
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode  = MeasureSpec.getMode(heightMeasureSpec);

        //使wrap_content, Padding生效
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = mRect.height() + getPaddingTop() + getPaddingBottom();
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = mRect.width() + + getPaddingLeft() + getPaddingRight();
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, width, height, mPaint);
        mPaint.setColor(mColor);
        canvas.drawText(mContent, 0, mContent.length(), width / 2 - mRect.width() / 2, height / 2 + mRect.height() / 2, mPaint);
    }

    public void setTextNum(int textNum) {
        this.mContent = String.valueOf(textNum);
        postInvalidate();
    }
}
