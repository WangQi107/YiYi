package com.yiyi.translater.activity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yiyi.translater.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchDictActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tvZd,tvDic;
    private EditText etZdSea;
    private ImageView ivZdSea;
    private JSONObject js;
    private String result = "";

    private String url = "http://v.juhe.cn/xhzd/query";//请求的网址
    private String key = "0494fbef4fba4f8fda8907ccd7c4e53a";//请求的key
    private String word = "";//需要查询的字

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String r = (String) msg.obj;
                System.out.println(r);
                try {
                    js = new JSONObject(r);
                    JSONArray value = js.getJSONArray("result");
                    JSONObject child = null;
                    for (int i = 0; i < value.length(); i++) {
                        child = value.getJSONObject(i);
                        result = child.getString("jijie");
                        tvDic.setText(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                }
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dict);
        initViews();
        setListeners();
    }

    private void setListeners() {
        etZdSea.setOnClickListener(this);
        ivZdSea.setOnClickListener(this);
    }

    private void initViews() {
        tvDic=findViewById(R.id.tv_dic);
        tvZd=findViewById(R.id.tv_zd);
        etZdSea=findViewById(R.id.et_zd_search);
        ivZdSea=findViewById(R.id.iv_zd_search);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_zd_search:
                    dict();
                    etZdSea.setText("");
                break;
        }
    }
        private void dict() {
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("key", key);//需要翻译的文字
            params.addQueryStringParameter("word", word);//翻译源语言
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<Object>() {
                @Override
                public void onSuccess(ResponseInfo<Object> responseInfo) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = responseInfo.result;
                    handler.sendMessage(msg);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(SearchDictActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }
}
