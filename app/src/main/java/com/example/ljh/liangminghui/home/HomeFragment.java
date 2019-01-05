package com.example.ljh.liangminghui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljh.liangminghui.R;
import com.example.ljh.liangminghui.utils.RetrofitUtils;
import com.example.ljh.liangminghui.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private EditText mEtSearch;
    private ImageView mIvSearch;
    private ProgressBar mProgressBar;
    private ListView mlvHome;
    private Spinner mSpinner;

    public static final String KEY = "3d7dc0f1d40b0ecebf5b9499c482b16b";
    public static final String BASE_URL = "http://v.juhe.cn/";

    private List<HomeBean> mDataList = new ArrayList<>();
    private List<String> mSpinnerList = new ArrayList<>();

    private ListViewAdapterHome mLvAdapter;
    ArrayAdapter<String> mSpinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        init();
        return view;
    }

    private void initView(View view){
        mEtSearch = view.findViewById(R.id.etSearch);
        mIvSearch = view.findViewById(R.id.ivSearch);
        mProgressBar = view.findViewById(R.id.progressBar);
        mlvHome = view.findViewById(R.id.lvHome);
        mSpinner = view.findViewById(R.id.spinnerHome);

        mIvSearch.setOnClickListener(this);
        /**
         * 监听软键盘回车键
         */
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    searchData(v.getText()+"");
                    SQLiteHelper.getInstance().insertSearchHistory(v.getText()+"");
                    getSearchHistory();
                }
                return false;
            }
        });
    }

    private void init(){
        mLvAdapter = new ListViewAdapterHome(getContext(),mDataList);
        mlvHome.setAdapter(mLvAdapter);

        mSpinnerAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_home,mSpinnerList);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = mSpinnerList.get(position);
                searchData(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSearchHistory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivSearch:
                searchData(mEtSearch.getText()+"");
                SQLiteHelper.getInstance().insertSearchHistory(mEtSearch.getText()+"");
                getSearchHistory();
                break;
        }
    }

    /**
     * 搜索关键词
     * @param s
     */
    private void searchData(String s){
        if(s == null || s.equals("")){
            return;
        }
        showProgressBar();
        RetrofitUtils.getInstance().getIRetrofitRx2Gson(BASE_URL)
                .searchData(KEY,s,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean.HomeResBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean.HomeResBean value) {
                        mDataList.clear();
                        List<HomeBean> list = value.getResult();
                        for(int i=0;i<list.size();i++){
                            mDataList.add(list.get(i));
                        }
                        hideProgressBar();
                        mLvAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressBar();
                        Toast.makeText(getContext(),e+"",Toast.LENGTH_SHORT).show();
                        Log.i("aaa","error = " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void getSearchHistory(){
        List<String> list = SQLiteHelper.getInstance().getSearchHistory();
        mSpinnerList.clear();
        for(int i=0;i<list.size();i++){
            mSpinnerList.add(list.get(i));
        }
        mSpinnerAdapter.notifyDataSetChanged();
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}
