package com.AnberR.urlconnectionPOST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.AnberR.urlconnection.R;
import com.google.gson.Gson;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class POST extends Activity {
	
	private Context mContext;
	private ArrayList<Object> arrayList;
	private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<Object>();
        
        
        
        //主方法直接调用就可以了
        JsonPost("http****************", getjsonData());
    }
    
	/**
	 * post数据
	 * @return
	 */
	public JSONObject getjsonData() {
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
			jsonObject.put("buildingID", Integer.parseInt(userID));  //post提交内容为Integer类型
			jsonObject.put("buildingID", userID);                    //post提交内容为String类型
			for (int i = 0; i < arrayList.size(); i++) {			//post提交内容为list类型
				array.put(arrayList.get(i));
			}
			jsonObject.put("list", array);
			
		} catch (JSONException e) {
		}
		return jsonObject;
	}
	
	/**
	 * POST提交
	 * @param path
	 * @param json
	 * @return
	 */
	public String JsonPost(final String path, final JSONObject json) {
		BufferedReader in = null;							//创建BufferedReader缓冲流
		String result = "";
		OutputStream os = null;
		HttpURLConnection hp = null;
		try {
			URL url = new URL(path);
			// 将json装成String类型
			String content = String.valueOf(json);
			hp = (HttpURLConnection) url.openConnection();
			// 设置请求头
			hp.setRequestMethod("POST");
			hp.setRequestProperty("Connection", "keep-alive");							//让post一直处于激活状态					
			hp.setRequestProperty("Content-Length",String.valueOf(content.getBytes().length));		//设定长度
			hp.setRequestProperty("Content-Type","application/json;charset=utf-8");				//post的请求格式 json/utf-8
			hp.setRequestProperty("User-Agent", "Fiddler");
			hp.setDoOutput(true); // 发送POST请求必须设置允许输出
			hp.setDoInput(true); // 发送POST请求必须设置允许输入
			hp.setConnectTimeout(5000);
			os = hp.getOutputStream();
			os.write(content.getBytes());
			os.flush();
			os.close();
			in = new BufferedReader(new InputStreamReader(hp.getInputStream()));
			int len = 0;
			byte buffer[] = new byte[1024];
			String line;
			if (hp.getResponseCode() == 200) {
				while ((line = in.readLine()) != null) {			//一行一行的读取数据流，原因比较快
					result += line;
				}
			}
			Gson gson = new Gson();											
			ListSellerBean addCustomer = gson.fromJson(result, ListSellerBean.class);	//接收post提交之后的内容
			// 释放资源
			in.close();
			os.close();
			hp.disconnect();
			if (addCustomer.status.equals("1")) {
				Toast.makeText(mContext, addCustomer.msg,Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mContext, addCustomer.msg,Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			try {
				if (in != null)
					in.close();
				if (os != null)
					os.close();
				if (hp != null)
					hp.disconnect();
				Toast.makeText(mContext, "系统异常",Toast.LENGTH_SHORT).show();
			} catch (IOException e1) {
			}
		}
		return result;
	}
	
    
}
