package com.AnberR.urlconnectionGET;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.AnberR.urlconnection.R;
import com.google.gson.Gson;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

public class GET extends Activity {
	
	private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //直接调用就可以了，不需要开启子线程
        getDataFromServer();
    }
    
    //GET HttpUrlConnection
    private void getDataFromServer() {
		InputStream is = null;
		String result = "";
		ByteArrayOutputStream os = null;
		HttpURLConnection hp = null;
		try {
			String Url ="http************";                              //需要输入你的接口地址
			URLEncoder.encode(Url, "UTF-8");			//设置编码格式
			// 输出URL
			System.out.println("输出URL的值：" + Url);
			URL url = new URL(Url);
			hp = (HttpURLConnection) url.openConnection();// 打开网络连接
			hp.setRequestMethod("GET");// 设置请求方式
			hp.setConnectTimeout(3000);// 设置设置超时时间
			hp.connect();
			// 获取响应的输入流对象
			is = hp.getInputStream();
			// 创建字节输出流对象
			os = new ByteArrayOutputStream();
			// 定义读取的长度
			int len = 0;
			// 定义缓冲区
			byte buffer[] = new byte[1024];
			// 按照缓冲区的大小，循环读取
			while ((len = is.read(buffer)) != -1) {
				// 根据读取的长度写入到os对象中
				os.write(buffer, 0, len);
			}
			result = new String(os.toByteArray());
			Gson gson = new Gson();
			ListSellerBean listsellerBean = gson.fromJson(result,ListSellerBean.class);
			if (listsellerBean.status.equals("2")) {
				//通过获取的后台数据来判断是否连接成功
				Toast.makeText(mContext, listsellerBean.msg, Toast.LENGTH_SHORT).show();
			} else {
				
			}
			// 释放资源
			is.close();
			os.close();
			hp.disconnect();
		} catch (Exception e) {
			try {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
				if (hp != null)
					hp.disconnect();
			} catch (IOException e1) {
			}
		}
	}
    
}
