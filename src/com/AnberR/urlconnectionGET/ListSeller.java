package com.AnberR.urlconnectionGET;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.google.gson.JsonObject;

@SuppressWarnings("serial")
public class ListSeller implements Serializable {

	public String tel1;
	public String stateID;
	public String userNo;
	public String marryID;
	public String customerCnt;
	public String unitPID;
	public String userID;
	public String buildingDesc;
	public String points;
	public String areaID;
	public String districtID;
	public String isPublic;
	public String subwayID;
	public String uname;
	public String huxingID;
	public String isDelete;
	public String sex;
	public String usex;
	public String mobile;
	public String photo;
	public String decoID;
	public String updateTime;
	public String cityID;
	public String customerDesc;
	public String floorID;
	public String totalPID;
	public String createTime;
	public String houseTID;
	public String mianjiID;
	public String schoolID;
	public String name;
	public String cname;
	public String customerNo;
	public Boolean isCheck;

	@Override
	public String toString() {
		String s = "";
		Field[] arr = this.getClass().getFields();
		for (Field f : getClass().getFields()) {
			try {
				s += f.getName() + "=" + f.get(this) + "\n,";
			} catch (Exception e) {
			}
		}
		return getClass().getSimpleName() + "["
				+ (arr.length == 0 ? s : s.substring(0, s.length() - 1)) + "]";
	}
}
