package com.sinaproject.contract.Presenter;

import android.content.Context;
import android.util.Log;

import com.mvprr.base.BaseModel;
import com.mvprr.progress.ObserverOnNextListener;
import com.sinaproject.api.Api;

import com.sinaproject.contract.WeiboContract;
import com.sinaproject.data.Constant;
import com.sinaproject.data.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by DeMon on 2017/11/5.
 */

public class WeiboPresenter extends WeiboContract.Presenter {
    private BaseModel<UserInfo> model = new BaseModel<>();
    private WeiboContract.View view;
    private Context context;

    public WeiboPresenter(Context context, WeiboContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getWeibo(Map<String, Object> map) {
        model.Subscribe(context, Api.getApiService().getWeibo(map), new ObserverOnNextListener() {
            @Override
            public void onNext(Object o) {
                try {
                    JSONObject object = new JSONObject((String) o);
                    JSONArray array = object.getJSONArray(Constant.STATUSES);
                    view.result(array.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
