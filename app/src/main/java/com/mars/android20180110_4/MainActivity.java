package com.mars.android20180110_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

        public void click1(View v)
    {   //Android目前規定所以網路的存取都不能以主執行緒執行
        new Thread(){
            @Override
            public void run() {
                super.run();
                //在網路上要做的事情寫在這裡
                String str_url = "https://www.google.com.tw";
                URL url = null;
                try {
                    //建立一個URL物件，帶入參數為想要建立HTTP連線的目的地，例如網站的網址。
                    url = new URL(str_url);
                    //建立一個HttpURLConnection物件，並利用URL的openConnection()來建立連線。
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //利用setRequestMethod()來設定連線的方式，一般分為POST及GET兩種
                    conn.setRequestMethod("GET");
                    conn.connect();
                    //連線取得的回應載入到一個InputStream中，然後就可以將InputStream的內容取出應用，以這個例子而言我們取得的會是網頁的原始碼。
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(isr);
                    String str = br.readLine();
                    //列出網頁內容
                    Log.d("NET", str);
                    br.close();
                    isr.close();
                    inputStream.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
