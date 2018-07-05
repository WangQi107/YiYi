package com.yiyi.translater.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SearchIdiomActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCi, tvIpy, tvSy, tvFrom, tvJinyi, tvFanyi;
    private EditText etIdiomSea;
    private ImageView ivIdiomSea;
    private RelativeLayout rlIdiomres;
    private JSONObject js;
    private String result = "";

    private String url = "http://api.jisuapi.com/cidian/word";//请求的网址
    private String key = "3c0a36ebd75e3739";//请求的key
    private String word = "";//需要查询的字

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String r = (String) msg.obj;
                readIdiomJson(r);
                System.out.println(r);
            }
        }
    };

    private String readIdiomJson(String jsonStr) {
        try {
            js = new JSONObject(jsonStr);
            JSONArray value = js.getJSONArray("result");
            JSONObject child = null;
            for (int i = 0; i < value.length(); i++) {
                child = value.getJSONObject(i);
                String ci = child.getString("name");
                String ipy = child.getString("pinyin");
                String shiyi = child.getString("chengyujs");
                String from = child.getString("from_");
                String jin = child.getString("tongyi");
                String fan = child.getString("fanyi");
                tvCi.setText(ci);
                tvIpy.setText(ipy);
                tvSy.setText(shiyi);
                tvFrom.setText(from);
                tvJinyi.setText(jin);
                tvFanyi.setText(fan);
                rlIdiomres.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_idiom);
        initViews();
        setListeners();
    }

    private void setListeners() {
        etIdiomSea.setOnClickListener(this);
        ivIdiomSea.setOnClickListener(this);
    }

    private void initViews() {
        rlIdiomres=findViewById(R.id.rl_idiomres);
        etIdiomSea=findViewById(R.id.et_idiom_search);
        ivIdiomSea=findViewById(R.id.iv_idiom_search);
        tvCi=findViewById(R.id.tv_ci);
        tvIpy=findViewById(R.id.tv_ipy);
        tvSy=findViewById(R.id.tv_sy);
        tvFrom=findViewById(R.id.tv_from);
        tvJinyi=findViewById(R.id.tv_jinyi);
        tvFanyi=findViewById(R.id.tv_fanyi);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_idiom_search:
                idiom();
                break;
        }
    }

    private void idiom() {
        word = etIdiomSea.getText().toString();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("key", key);
        params.addQueryStringParameter("word", word);
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
                Toast.makeText(SearchIdiomActivity.this, "错误！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
