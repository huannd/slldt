package com.dtcs.slldt.model;

public class ResultModel {

	public String strResult;
	
	public int getErrorCode(){
		Integer ret = -1;
		if (strResult!=null) {
			if (strResult.contains("|")) {
				try {
					String first = strResult.split("\\|")[0];
					ret = Integer.parseInt(first);
				} catch (NumberFormatException e) {
					return -1;
				}
			}
		}
		return ret;
	}
	
	public String getErrorDescription(){
		if (strResult == null) return "";
		if (strResult.contains("|")) {
			return strResult.split("\\|")[1];
		}else {
			return "";
		}
	}
}
