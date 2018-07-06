package com.yiyi.translater.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchDictActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tvZi, tvBihua,tvPy,tvBushou,tvJijie;
    private RelativeLayout rlRes;
    private EditText etZdSea;
    private ImageView ivZdSea;
    private JSONObject js;
    private String result = "";
    private Toolbar toolbar;

    private String url = "http://api.avatardata.cn/XinHuaZiDian/LookUp";//请求的网址
    private String key = "2876cc5b612a46efac770967ae85f4cf";//请求的key
    private String content  = "";//需要查询的字

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String r = (String) msg.obj;
                readJson(r);
                System.out.println(r);
            }
        }
    };
    private String readJson(String jsonStr) {
        try {
            js = new JSONObject(jsonStr);
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(jsonStr);
            jsonStr = m_html.replaceAll(""); // 过滤html标签
            JSONArray value = js.getJSONArray("result");
            JSONObject child = null;
            for (int i = 0; i < value.length(); i++) {
                child = value.getJSONObject(i);
                String a=jsonStr.trim().replaceAll(" ", "");
                js = new JSONObject(a);
                JSONArray v = js.getJSONArray("result");
                JSONObject c = null;
                c=v.getJSONObject(i);
                String zi = c.getString("hanzi");
                String py = c.getString("duyin");
                String bihua = c.getString("bihua");
                String bushou = c.getString("bushou");
                String jianjie = c.getString("jianjie");
                tvZi.setText(zi);
                tvPy.setText(py);
                tvBihua.setText(bihua);
                tvBushou.setText(bushou);
                tvJijie.setText(jianjie);
                rlRes.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStr.trim().replaceAll(" ", ""); // 返回文本字符串
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dict);
        initViews();
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setListeners();
    }

    private void setListeners() {
        etZdSea.setOnClickListener(this);
        ivZdSea.setOnClickListener(this);
    }

    private void initViews() {
        tvJijie=findViewById(R.id.tv_jijie);
        tvBushou=findViewById(R.id.tv_bushou);
        tvPy= findViewById(R.id.tv_py);
        tvBihua = findViewById(R.id.tv_bihua);
        tvZi = findViewById(R.id.tv_zi);
        etZdSea = findViewById(R.id.et_zd_search);
        ivZdSea = findViewById(R.id.iv_zd_search);
        rlRes = findViewById(R.id.rl_res);
        toolbar=findViewById(R.id.dict_toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_zd_search:
                dict();
                break;
        }
    }

    private void dict() {
        content = etZdSea.getText().toString();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("key", key);
        params.addQueryStringParameter("content", content);
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
