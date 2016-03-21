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
        
        
        
        //������ֱ�ӵ��þͿ�����
        JsonPost("http****************", getjsonData());
    }
    
	/**
	 * post����
	 * @return
	 */
	public JSONObject getjsonData() {
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
			jsonObject.put("buildingID", Integer.parseInt(userID));  //post�ύ����ΪInteger����
			jsonObject.put("buildingID", userID);                    //post�ύ����ΪString����
			for (int i = 0; i < arrayList.size(); i++) {			//post�ύ����Ϊlist����
				array.put(arrayList.get(i));
			}
			jsonObject.put("list", array);
			
		} catch (JSONException e) {
		}
		return jsonObject;
	}
	
	/**
	 * POST�ύ
	 * @param path
	 * @param json
	 * @return
	 */
	public String JsonPost(final String path, final JSONObject json) {
		BufferedReader in = null;							//����BufferedReader������
		String result = "";
		OutputStream os = null;
		HttpURLConnection hp = null;
		try {
			URL url = new URL(path);
			// ��jsonװ��String����
			String content = String.valueOf(json);
			hp = (HttpURLConnection) url.openConnection();
			// ��������ͷ
			hp.setRequestMethod("POST");
			hp.setRequestProperty("Connection", "keep-alive");							//��postһֱ���ڼ���״̬					
			hp.setRequestProperty("Content-Length",String.valueOf(content.getBytes().length));		//�趨����
			hp.setRequestProperty("Content-Type","application/json;charset=utf-8");				//post�������ʽ json/utf-8
			hp.setRequestProperty("User-Agent", "Fiddler");
			hp.setDoOutput(true); // ����POST������������������
			hp.setDoInput(true); // ����POST�������������������
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
				while ((line = in.readLine()) != null) {			//һ��һ�еĶ�ȡ��������ԭ��ȽϿ�
					result += line;
				}
			}
			Gson gson = new Gson();											
			ListSellerBean addCustomer = gson.fromJson(result, ListSellerBean.class);	//����post�ύ֮�������
			// �ͷ���Դ
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
				Toast.makeText(mContext, "ϵͳ�쳣",Toast.LENGTH_SHORT).show();
			} catch (IOException e1) {
			}
		}
		return result;
	}
	
    
}
