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

public class SearchIdiomActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCi, tvIpy, tvSy, tvFrom, tvJinyi, tvFanyi;
    private EditText etIdiomSea;
    private ImageView ivIdiomSea;
    private RelativeLayout rlIdiomres;
    private JSONObject js;
    private String result = "";
    private Toolbar toolbar;

    private String url = "http://v.juhe.cn/chengyu/query";//请求的网址
    private String key = "27e386edf3f21c5f4fd052e23b8a0b36";//请求的key
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
            JSONObject value = js.getJSONObject("result");
            for (int i = 0; i < value.length(); i++) {
                String ipy = value.getString("pinyin");
                String shiyi = value.getString("chengyujs");
                String from = value.getString("from_");
                String jin = value.getString("tongyi");
                String fan = value.getString("fanyi");
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
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        toolbar=findViewById(R.id.chenyu_toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_idiom_search:
                idiom();
                tvCi.setText(word);
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
