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
        //ֱ�ӵ��þͿ����ˣ�����Ҫ�������߳�
        getDataFromServer();
    }
    
    //GET HttpUrlConnection
    private void getDataFromServer() {
		InputStream is = null;
		String result = "";
		ByteArrayOutputStream os = null;
		HttpURLConnection hp = null;
		try {
			String Url ="http************";                              //��Ҫ������Ľӿڵ�ַ
			URLEncoder.encode(Url, "UTF-8");			//���ñ����ʽ
			// ���URL
			System.out.println("���URL��ֵ��" + Url);
			URL url = new URL(Url);
			hp = (HttpURLConnection) url.openConnection();// ����������
			hp.setRequestMethod("GET");// ��������ʽ
			hp.setConnectTimeout(3000);// �������ó�ʱʱ��
			hp.connect();
			// ��ȡ��Ӧ������������
			is = hp.getInputStream();
			// �����ֽ����������
			os = new ByteArrayOutputStream();
			// �����ȡ�ĳ���
			int len = 0;
			// ���建����
			byte buffer[] = new byte[1024];
			// ���ջ������Ĵ�С��ѭ����ȡ
			while ((len = is.read(buffer)) != -1) {
				// ���ݶ�ȡ�ĳ���д�뵽os������
				os.write(buffer, 0, len);
			}
			result = new String(os.toByteArray());
			Gson gson = new Gson();
			ListSellerBean listsellerBean = gson.fromJson(result,ListSellerBean.class);
			if (listsellerBean.status.equals("2")) {
				//ͨ����ȡ�ĺ�̨�������ж��Ƿ����ӳɹ�
				Toast.makeText(mContext, listsellerBean.msg, Toast.LENGTH_SHORT).show();
			} else {
				
			}
			// �ͷ���Դ
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
