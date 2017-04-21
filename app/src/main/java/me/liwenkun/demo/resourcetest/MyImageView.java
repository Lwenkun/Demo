package me.liwenkun.demo.resourcetest;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import me.liwenkun.demo.R;

/**
 * Created by lwenkun on 2017/4/17.
 */

public class MyImageView extends AppCompatImageView {

    private static String TAG = "ImageView";

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        printAttrs(attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyImageView);
        a.getDrawable(R.styleable.MyImageView_background);
        a.recycle();
//        typedArray.getDimension()

    }

    private void printAttrs(AttributeSet set) {
        for (int i = 0; i < set.getAttributeCount(); i++) {
            Log.d(TAG, "getAttributeName:" + set.getAttributeName(i) + ",getAttributeValue:" + set.getAttributeValue(i));
            Log.d(TAG, "getAttributeIntValue:" + set.getAttributeIntValue(i, -10));
            Log.d(TAG, "getAttributeBooleanValue:" + set.getAttributeBooleanValue(i, false));
            Log.d(TAG, "getAttributeResourceValue:" + set.getAttributeResourceValue(i, -10));
        }
        Log.d(TAG, "getIdAttribute:" + set.getIdAttribute());
        Log.d(TAG, "getIdAttributeResourceValue:" + set.getIdAttributeResourceValue(-10));

        /**
         * result :
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute name:background,attribute value:@2131558420
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute int value:-10
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute boolean value:false
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute resource value:2131558420
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute name:clickable,attribute value:true
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute int value:-1
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute boolean value:true
         04-17 12:20:35.833 18613-18613/me.liwenkun.demo D/ImageView: attribute resource value:-10
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute name:layout_width,attribute value:@2131296285
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute int value:-10
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute boolean value:false
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute resource value:2131296285
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute name:layout_height,attribute value:-2
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute int value:-2
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute boolean value:true
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute resource value:-10
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute name:src,attribute value:@2130903040
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute int value:-10
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute boolean value:false
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute resource value:2130903040
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute name:contentDescription,attribute value:@2131230754
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute int value:-10
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute boolean value:false
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: attribute resource value:2131230754
         04-17 12:20:35.834 18613-18613/me.liwenkun.demo D/ImageView: getIdAttribute:null
         04-17 12:20:35.835 18613-18613/me.liwenkun.demo D/ImageView: getIdAttributeResourceValue:-1
         */
    }
}
