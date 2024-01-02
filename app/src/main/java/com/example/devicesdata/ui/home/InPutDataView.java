package com.example.devicesdata.ui.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.devicesdata.R;

/**
 * TODO: document your custom view class.
 */
public class InPutDataView extends View {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private Button buttonTop1, buttonTop2;
    private EditText editTextTop1, editTextTop2;

    public InPutDataView(Context context) {
        super(context);
        init(null, 0);
    }

    public InPutDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public InPutDataView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.InPutDataView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.InPutDataView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.InPutDataView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.InPutDataView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.InPutDataView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.InPutDataView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();



    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        config();
    }

    public void btn1Click(View view){
        // 处理按钮1的点击事件
        // 可以在这里调用外部接口方法通知点击事件
        editTextTop1 = findViewById(R.id.editTextTop1);
        Log.d("111111","按钮1点击了");
        // 获取输入框1的文本
        String url = editTextTop1.getText().toString();
        if (url.isEmpty()){
            return;
        }
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        Log.d("111111","加载的地址是:"+ url);
        DataManager.getInstance().webview.loadUrl(url);
    }

    public void btn2Click(View view){
        editTextTop2 = findViewById(R.id.editTextTop2);
        // 处理按钮2的点击事件
        // 可以在这里调用外部接口方法通知点击事件
        Log.d("111111","按钮2点击了");
        String deviceip = editTextTop2.getText().toString();
        if (deviceip.isEmpty()){
            return;
        }
        getDviceData(deviceip);
    }

    public void  config(){
        Log.d("111111", "InPutDataView onAttachedToWindow: ");
        // 找到按钮和输入框
        buttonTop1 = findViewById(R.id.buttonTop1);
        buttonTop2 = findViewById(R.id.buttonTop2);


        // 设置按钮点击事件监听器
        buttonTop1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonTop2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getDviceData(String deviceip){

    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }
        config();
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view"s example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view"s example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view"s example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view"s example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 隐藏键盘
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindowToken(), 0);

            // 返回 true 表示消耗了触摸事件
            return true;
        }
        return super.onTouchEvent(event);
    }
}