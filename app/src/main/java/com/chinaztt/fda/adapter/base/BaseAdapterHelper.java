/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chinaztt.fda.adapter.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Allows an abstraction of the ViewHolder pattern.<br>
 * <br>
 * <p/>
 * <b>Usage</b>
 * <p/>
 * <pre>
 * return BaseAdapterHelper.get(context, convertView, parent, R.layout.item)
 *         .setText(R.id.tvName, contact.getName())
 *         .setText(R.id.tvEmails, contact.getEmails().toString())
 *         .setText(R.id.tvNumbers, contact.getNumbers().toString())
 *         .getView();
 * </pre>
 */

/**
 * Adapter基类，其中封装了方法
 */
public class BaseAdapterHelper {
    /** Views indexed with their IDs */
    //进行那个存储模板中的View控件-稀疏数组
    private final SparseArray<View> views;
    private final Context context;
    private int position;
    //列表item  view
    private View convertView;
    /** Package private field to retain the associated user object and detect a change */
    Object associatedObject;

    protected BaseAdapterHelper(Context context, ViewGroup parent, int layoutId, int position) {
        this.context = context;
        this.position = position;
        this.views = new SparseArray<View>();
        convertView = LayoutInflater.from(context) //根据布局id来加载view
                .inflate(layoutId, parent, false);
        convertView.setTag(this);//相当于存放ViewHolder
    }

    /**
     * This method is the only entry point to get a BaseAdapterHelper.
     * @param context     The current context.
     * @param convertView The convertView arg passed to the getView() method.
     * @param parent      The parent arg passed to the getView() method.
     * @return A BaseAdapterHelper instance.
     */
    public static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId) {
        return get(context, convertView, parent, layoutId, -1);
    }

    /** This method is package private and should only be used by QuickAdapter. */
    /**
     * 进行获取类ViewHolder的BaseAdapterHelper对象
     * @param context      上下文引用
     * @param convertView   item view
     * @param parent        父控件view
     * @param layoutId       布局ID
     * @param position      索引
     * @return
     */
    static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseAdapterHelper(context, parent, layoutId, position);
        }
        // Retrieve the existing helper and update its position
        BaseAdapterHelper existingHelper = (BaseAdapterHelper) convertView.getTag();
        existingHelper.position = position;
        return existingHelper;
    }
    /**
     * 该方法public类型，可以让我们在外部自己获取相关的控件
     * This method allows you to retrieve a view and perform custom
     * operations on it, not covered by the BaseAdapterHelper.<br/>
     * If you think it's a common use case, please consider creating
     * a new issue at https://github.com/JoanZapata/base-adapter-helper/issues.
     * @param viewId The id of the view you want to retrieve.
     */
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    /**
     * Will set the text of a TextView.
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * Will set background color of a view.
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     * 设置view的背景颜色
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setBackgroundRes(int viewId, int backgroundRes) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     * 设置textview的字的颜色
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setTextColor(int viewId, int textColor) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Will set text color of a TextView.
     * @param viewId       The view id.
     * @param textColorRes The text color resource id.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setTextColorRes(int viewId, int textColorRes) {
        TextView view = retrieveView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Will download an image from a URL and put it in an ImageView.<br/>
     * It uses Square's Picasso library to download the image asynchronously and put the result into the ImageView.<br/>
     * Picasso manages recycling of views in a ListView.<br/>
     * If you need more control over the Picasso settings, use {BaseAdapterHelper#setImageBuilder}.
     * @param viewId   The view id.
     * @param imageUrl The image URL.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setImageUrl(int viewId, String imageUrl) {
        ImageView view = retrieveView(viewId);
        Picasso.with(context).load(imageUrl).into(view);
        return this;
    }

    /**
     * Will download an image from a URL and put it in an ImageView.<br/>
     * 下载图片 显示在imageView中
     * @param viewId         The view id.
     * @param requestBuilder The Picasso request builder. (e.g. Picasso.with(context).load(imageUrl))
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setImageBuilder(int viewId, RequestCreator requestBuilder) {
        ImageView view = retrieveView(viewId);
        requestBuilder.into(view);
        return this;
    }


    /** Add an action to set the image of an image view. Can be called multiple times. */
    /**
     * 给ImageView显示bitmap
     * @param viewId
     * @param bitmap
     * @return
     */
    public BaseAdapterHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     * 设置控件的显示的透明度
     */
    public BaseAdapterHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or GONE (false).
     * 控制view的现实和隐藏
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for GONE.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setVisible(int viewId, boolean visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * Add links into a TextView.
     * 给TextView添加超链接
     * @param viewId The id of the TextView to linkify.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /** Apply the typeface to the given viewId, and enable subpixel rendering. */
    /**
     * 设置字体格式
     * @param viewId
     * @param typeface
     * @return
     */
    public BaseAdapterHelper setTypeface(int viewId, Typeface typeface) {
        TextView view = retrieveView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /** Apply the typeface to all the given viewIds, and enable subpixel rendering. */
    /**
     * 设置字体 给多个控件添加
     * @param typeface
     * @param viewIds
     * @return
     */
    public BaseAdapterHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = retrieveView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     * 设置进度
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     * 设置进度
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     * 设置进度bar的最大进度
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setMax(int viewId, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     * 设置评分条的分数
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setRating(int viewId, float rating) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setRating(int viewId, float rating, int max) {
        RatingBar view = retrieveView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the on click listener of the view.
     * @param viewId   The view id.
     * @param listener The on click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * Sets the on touch listener of the view.
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = retrieveView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * Sets the on long click listener of the view.
     * @param viewId   The view id.
     * @param listener The on long click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * Sets the listview or gridview's item click listener of the view
     * @param viewId  The view id.
     * @param listener The item on click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setOnItemClickListener(int viewId,AdapterView.OnItemClickListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }
    /**
     * Sets the listview or gridview's item long click listener of the view
     * @param viewId The view id.
     * @param listener   The item long click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setOnItemLongClickListener(int viewId,AdapterView.OnItemLongClickListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }
    /**
     * Sets the listview or gridview's item selected click listener of the view
     * @param viewId The view id.
     * @param listener The item selected click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setOnItemSelectedClickListener(int viewId,AdapterView.OnItemSelectedListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }
    /**
     * Sets the tag of the view.
     * @param viewId The view id.
     * @param tag    The tag;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setTag(int viewId, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the tag of the view.
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setTag(int viewId, int key, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) retrieveView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseAdapterHelper setAdapter(int viewId, Adapter adapter) {
        AdapterView view = retrieveView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /** Retrieve the convertView */
    /**
     * 返回列表item view
     */
    public View getView() {
        return convertView;
    }

    /**
     * Retrieve the overall position of the data in the list.
     * @throws IllegalArgumentException If the position hasn't been set at the construction of the this helper.
     */
    public int getPosition() {
        if (position == -1)
            throw new IllegalStateException("Use BaseAdapterHelper constructor " +
                    "with position if you need to retrieve the position.");
        return position;
    }

    @SuppressWarnings("unchecked")
    /**
     * 进行从模板view中获取相应的控件 然后放入到view控件集合中
     */
    protected <T extends View> T retrieveView(int viewId) {
        //从集合中获取当前viewId的view，如果集合中不存在，那么从view重findById()
        //获取出来存放到集合中 并且返回
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /** Retrieves the last converted object on this view. */
    public Object getAssociatedObject() {
        return associatedObject;
    }

    /** Should be called during convert */
    public void setAssociatedObject(Object associatedObject) {
        this.associatedObject = associatedObject;
    }
}
