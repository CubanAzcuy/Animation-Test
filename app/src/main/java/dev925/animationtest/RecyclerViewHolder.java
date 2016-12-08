package dev925.animationtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robertgross on 12/7/16.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.label_1)
    protected TextView mLabel1;

    @BindView(R.id.label_2)
    protected TextView mLabel2;

    public static RecyclerViewHolder newInstance(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_viewholder, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    public RecyclerViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public TextView getLabel1() {
        return mLabel1;
    }

    public void setLabel1(TextView label1) {
        this.mLabel1 = label1;
    }

    public TextView getLabel2() {
        return mLabel2;
    }

    public void setLabel2(TextView label2) {
        this.mLabel2 = label2;
    }

    public void bind(Object obj) {
        mLabel1.setText((String)obj);
    }
}
