package com.example.kk.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.kk.customview.R;

import static android.view.View.MeasureSpec.AT_MOST;

public class CustomImageView extends View {

    private String mTitleText;
    private int mTitleSize;
    private int mTitleColor;
    private Drawable mImage;
    private int mImageScaleType;

    private static final int FILL_XY = 0;
    private static final int CENTER = 1;

    private Paint mPaint;
    private Rect mRect;

    private int mImageWidth;
    private int mImageHeight;
    private int mTextWidth;
    private int mTextHeight;

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        mTitleText = ta.getString(R.styleable.CustomImageView_titleText);
        mTitleSize = ta.getDimensionPixelSize(R.styleable.CustomImageView_titleTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        mTitleColor = ta.getColor(R.styleable.CustomImageView_titleTextColor, 0);
        mImage = ta.getDrawable(R.styleable.CustomImageView_image);
        mImageScaleType = ta.getInt(R.styleable.CustomImageView_imageScaleType, FILL_XY);

        mPaint = new Paint();
        mRect = new Rect();
        mPaint.setTextSize(mTitleSize);
        mPaint.getTextBounds(mTitleText, 0 , mTitleText.length(), mRect);
        mPaint.setColor(mTitleColor);
        mTextHeight = mRect.height();
        mTextWidth = mRect.width();
        mImageWidth = mImage.getIntrinsicWidth();
        mImageHeight = mImage.getIntrinsicHeight();

        ta.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = widthSize;
        int height = heightSize;

        if (widthMode == MeasureSpec.AT_MOST) {
            width = mTextWidth > mImageWidth ? mTextWidth : mImageWidth + getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            height = mTextHeight + mImageHeight + getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //文字
        TextPaint paint = new TextPaint(mPaint);
        if (mTextWidth > height) {
            String msg = (String) TextUtils.ellipsize(mTitleText, paint, width - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END);
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            canvas.drawText(msg, getPaddingLeft(), height - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mTitleText, width / 2 - mTextWidth / 2, getPaddingTop() + mImageHeight + mTextHeight, mPaint);
        }
        // 边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, width, height, mPaint);

        //　图片
        if (mImageScaleType == FILL_XY) {
            mImage.setBounds(getPaddingLeft(), getPaddingTop(), width - getPaddingRight(), height - getPaddingBottom() - mTextHeight);
        } else {
            mImage.setBounds(width / 2 - mImageWidth / 2, getPaddingTop(), width / 2 + mImageWidth / 2, getPaddingTop() + mImageHeight);
        }
        mImage.draw(canvas);

    }
}
