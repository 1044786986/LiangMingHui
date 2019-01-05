package com.example.ljh.liangminghui.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ljh.liangminghui.R;
import com.example.ljh.liangminghui.utils.GetUrlImageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalFragment extends Fragment {
    private GridView mGridView;
    private TextView mTvLogin;
    private ImageView mIvHead;

    private SimpleAdapter mSimpleAdapter;

    private int[] icon={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher
            ,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] iconName={"通讯录","日历","照相机","时钟","游戏"
            ,"短信","铃声","设置","语音","天气","浏览器","视频"};
    private List<Map<String,Object>> mDataList = new ArrayList<>();

    private final String HEAD_URL = "http://www.liangjiehao.top/logistics/notice/1.png";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        initView(view);
        init();
        return view;
    }

    private void initView(View view){
        mIvHead = view.findViewById(R.id.ivHead);
        mGridView = view.findViewById(R.id.gridView);
        mTvLogin = view.findViewById(R.id.tvLogin);
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getContext().getSharedPreferences("lmh",Context.MODE_PRIVATE);
                String username = sp.getString("username","");
                mTvLogin.setText(username);
            }
        });
    }

    private void init(){
        /**
         * 初始化gridView
         */
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            mDataList.add(map);
        }
        mSimpleAdapter = new SimpleAdapter(getContext(),mDataList,R.layout.gridview_personal,
                new String[]{"image","text"},new int[]{R.id.imageView,R.id.textView});
        mGridView.setAdapter(mSimpleAdapter);

        /**
         * 获取头像
         */
        GetUrlImageUtils.getUrlImage(HEAD_URL, new GetUrlImageUtils.UrlImageCallback() {
            @Override
            public void onSuccess(byte[] bytes) {
                mIvHead.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
            }

            @Override
            public void onFailed(String error) {

            }
        });
    }
}
