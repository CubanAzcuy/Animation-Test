package dev925.animationtest;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by robertgross on 12/7/16.
 */

public class RecyclerViewAdapter extends Adapter<RecyclerViewHolder>  {
    protected ArrayList<Object> _items;

    public void setCollection(ArrayList<Object> items){
        _items = items;
    }

    public ArrayList<Object> getCollection() {
        return _items;
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder = RecyclerViewHolder.newInstance(parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        Object item = _items.get(position);
        viewHolder.bind(item);
    }
}

