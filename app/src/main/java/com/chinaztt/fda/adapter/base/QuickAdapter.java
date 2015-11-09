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
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static com.chinaztt.fda.adapter.base.BaseAdapterHelper.get;


/**
 * Abstraction class of a BaseAdapter in which you only need
 * to provide the convert() implementation.<br/>
 * Using the provided BaseAdapterHelper, your code is minimalist.
 * @param <T> The type of the items in the list.
 */
public abstract class QuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    /**
     * Create a QuickAdapter.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public QuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public QuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    /**
     * 进行获取类ViewHolder的  BaseAdapterHelper
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return
     */
    protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        return get(context, convertView, parent, layoutResId, position);
    }

}
