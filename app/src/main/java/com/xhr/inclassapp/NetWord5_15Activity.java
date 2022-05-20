package com.xhr.inclassapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xhr.inclassapp.databinding.ActivityNetwork515Binding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NetWord5_15Activity extends AppCompatActivity {

    private ActivityNetwork515Binding binding;
    public final static int WEATHER_ID = 1;
    public final static String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini";

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == WEATHER_ID) {
                final Weather5_16 weather = (Weather5_16) msg.obj;
                if (weather.getDesc().equals("OK")) {
                    Toast.makeText(NetWord5_15Activity.this, weather.getData().getCity(), Toast.LENGTH_SHORT).show();

                    String result =
                            "\n当前温度: " + weather.getData().getWendu() +
                                    "\n当前建议: " + weather.getData().getGanmao() +
                                    "\n今天天气: " + weather.getData().getForecast().get(0).getType() +
                                    "\n明天天气: " + weather.getData().getForecast().get(1).getType() +
                                    "\n后天天气: " + weather.getData().getForecast().get(2).getType();
                    binding.result.setText(result);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetwork515Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.requestBaidu.setOnClickListener(view -> new Thread(() -> {
            String result = NetWord5_15Activity.this.doGet("https://www.baidu.com", "");
            if (!TextUtils.isEmpty(result)) {
                NetWord5_15Activity.this.runOnUiThread(() -> binding.result.setText(result));
            }
        }).start());

        binding.getWeather.setOnClickListener(view -> new Thread(() -> {
            Map<String, Object> params = new HashMap<>();
            params.put("city", binding.city.getText());
            String result = doGet(WEATHER_URL, urlencode(params));
            if (!TextUtils.isEmpty(result)) {
                Weather5_16 weather = new Gson().fromJson(result, Weather5_16.class);

                Message msg = handler.obtainMessage();
                msg.what = WEATHER_ID;
                msg.obj = weather;
                handler.sendMessage(msg);

            } else {
                NetWord5_15Activity.this.runOnUiThread(() -> {
                    binding.result.setText(result);
                });
            }
        }).start());

        binding.getHttp.setOnClickListener(view -> new Thread(() -> {

            // get请求
            if (false) {
                String result = doGet("http://10.0.2.2:8080/hello", null);
                NetWord5_15Activity.this.runOnUiThread(() -> binding.result.setText(!TextUtils.isEmpty(result) ? result : "no data"));
            }

            // post请求
            if (true) {
                User user = new User("xhr","123");
                login("http://10.0.2.2:8080/login", user);
            }

            if (false) {
                Map<String, Object> params = new HashMap<>();
                params.put("token", binding.city.getText());
                String result = doGet("http://101.34.219.81:7012/Lj", urlencode(params));
                NetWord5_15Activity.this.runOnUiThread(() -> binding.result.setText(!TextUtils.isEmpty(result) ? result : "no data"));
            }

        }).start());
    }

    private void login(String url, User user) {
        String json = new Gson().toJson(user);
        final String result = doPost(url, json);
        NetWord5_15Activity.this.runOnUiThread(() -> binding.result.setText(!TextUtils.isEmpty(result) ? result : "no data"));
    }

    private String doGet(String httpUrl, String queryParam) {
        String result = null;
        try {
            if (!TextUtils.isEmpty(queryParam)) {
                httpUrl += "?" + queryParam;
            }
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream is = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append(System.getProperty("line.separator"));
            }
            result = buffer.toString();
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String doPost(String httpUrl, String queryParam) {
        String result = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("content-type", "application/json; utf-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(queryParam);
            writer.flush();
            writer.close();
            os.close();

            InputStream is = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append(System.getProperty("line.separator"));
            }
            result = buffer.toString();
            reader.close();
            is.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String urlencode(Map<String, ?> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            try {
                builder.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue() + "", "UTF-8"))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            final String queryStr = builder.toString();
            return queryStr.substring(0, queryStr.lastIndexOf("&"));
        }
        return null;
    }

    static class User{
        String login;
        String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

}