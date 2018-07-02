package com.yiyi.translater.fragment;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.yiyi.translater.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Translate extends Fragment implements View.OnClickListener {
    private View v;
    private Button from;
    private Button to;
    private EditText ett;
    private ImageView iclean;
    private ImageView iok;
    private TextView tvclean;
    private TextView tvres;
    private JSONObject js;
    private String result = "";
    private boolean net = false;

    private String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";//请求的网址
    private String q = "";//需要翻译的文字
    private String f = "auto";//翻译源语言
    private String t = "zh";//译文语言
    private String aid = "20180516000160623";//APP ID
    private String salt = "1435660288";//随机数
    private String secretkey = "U_aL8_6aRQSRgOmQYmQA";//密钥

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String r = (String) msg.obj;
                System.out.println(r);
                try {
                    js = new JSONObject(r);
                    JSONArray value = js.getJSONArray("trans_result");
                    JSONObject child = null;
                    for (int i = 0; i < value.length(); i++) {
                        child = value.getJSONObject(i);
                        result = child.getString("dst");
                        tvres.setText(result);
                        tvres.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    try {
                        String errorCode = js.getString("error_code");
                        if ("52001".equals(errorCode)) {
                            Toast.makeText(getActivity(), "请求超时，请重试",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("52002".equals(errorCode)) {
                            Toast.makeText(getActivity(), "系统错误，请重试",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("52003".equals(errorCode)) {
                            Toast.makeText(getActivity(), "未授权用户，请检查您的appid是否正确",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("54000".equals(errorCode)) {
                            Toast.makeText(getActivity(), "必填参数为空，请检查是否少传参数",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("58000".equals(errorCode)) {
                            Toast.makeText(getActivity(), "客户端IP非法，请检查您填写的IP地址是否正确可修改您填写的服务器IP地址",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("54001".equals(errorCode)) {
                            Toast.makeText(getActivity(), "签名错误，请检查您的签名生成方法",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("54003".equals(errorCode)) {
                            Toast.makeText(getActivity(), "访问频率受限，请降低您的调用频率",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("58001".equals(errorCode)) {
                            Toast.makeText(getActivity(), "译文语言方向不支持，请检查译文语言是否在语言列表里",
                                    Toast.LENGTH_SHORT).show();
                        } else if ("54004".equals(errorCode)) {
                            Toast.makeText(getActivity(), "账户余额不足，请前往管理控制台为账户充值",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        }
    };

    public Fragment_Translate() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_translate, container, false);
        initViews();
        checknet();
        if (net == false) {
            Toast.makeText(getActivity(), getString(R.string.neterror), Toast.LENGTH_SHORT).show();
        }
        setListeners();
        return v;
    }

    private void initViews() {
        from = v.findViewById(R.id.languagefrom);
        to = v.findViewById(R.id.languageto);
        ett = v.findViewById(R.id.et_t);
        iclean = v.findViewById(R.id.im_clean);
        iok = v.findViewById(R.id.im_ok);
        tvclean = v.findViewById(R.id.tv_clean);
        tvres=v.findViewById(R.id.tv_res);
    }

    private void setListeners() {
        from.setOnClickListener(this);
        to.setOnClickListener(this);
        iclean.setOnClickListener(this);
        iok.setOnClickListener(this);
        tvclean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_ok:
                checknet();
                if (net == true) {
                    q = ett.getText().toString();
                    translate();
                } else {
                    Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.im_clean:
                ett.setText("");
                tvclean.setText("");
                break;
        }
    }

    private void translate() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("q", q);//需要翻译的文字
        params.addQueryStringParameter("from", f);//翻译源语言
        params.addQueryStringParameter("to", t);//译文语言
        params.addQueryStringParameter("appid", aid);//APP ID
        params.addQueryStringParameter("salt", salt);//随机数
        params.addQueryStringParameter("sign", md5(aid + q + salt + secretkey));//签名
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
                Toast.makeText(getActivity(), "错误!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checknet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            net = false;
        } else {
            net = true;
        }
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
