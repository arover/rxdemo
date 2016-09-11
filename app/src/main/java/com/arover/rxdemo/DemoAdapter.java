package com.arover.rxdemo;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author arover
 *         created at 9/11/16 22:32
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder>{
    private final Context mContext;
    private final ArrayList<Item> mList;
    private final LayoutInflater mInflater;
    private View.OnClickListener mItemClickListener;

    public DemoAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = new ArrayList<Item>();
    }

    public ArrayList<Item> getList() {
        return mList;
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        mItemClickListener = listener;
    }
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item_demo,parent,false));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = getList().get(position);
        holder.itemView.setTag(item);
        holder.itemView.setOnClickListener(mItemClickListener);
        holder.label.setText(item.name);
    }

    @Override public int getItemCount() {
        return mList.size();
    }

    public static class Item {
        int id;
        String name;

        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView label;
        public ViewHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView;
        }
    }
}
