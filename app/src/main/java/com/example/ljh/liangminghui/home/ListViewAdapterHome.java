package com.example.ljh.liangminghui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ljh.liangminghui.R;

import java.util.List;

public class ListViewAdapterHome extends BaseAdapter {
    private List<HomeBean> mDataList;
    private Context mContext;

    public ListViewAdapterHome(Context context,List<HomeBean> list){
        mDataList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home,parent,false);
        TextView tvContent = view.findViewById(R.id.tvContent);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(mDataList.get(position).getTitle());
        tvContent.setText(mDataList.get(position).getDes());
        return view;
    }
}
