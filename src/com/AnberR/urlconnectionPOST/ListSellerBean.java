package com.AnberR.urlconnectionPOST;

import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ListSellerBean implements Serializable {

	public String msg;
	public String status;
	public String cnt;
	public List<ListSeller> list;

	
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