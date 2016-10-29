package com.goo.iacaculator.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.goo.iacaculator.R;


/**
 * Created by Goo on 2016-10-19.
 */

public class RVMainViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle;
    public TextView tvDescription;

    public RVMainViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
    }
}
