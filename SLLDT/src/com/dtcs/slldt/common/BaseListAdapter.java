package com.dtcs.slldt.common;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter<T> extends BaseAdapter{

	protected ArrayList<T> mListDatas;
	protected Activity mActivity;
	
	public BaseListAdapter(Activity activity,ArrayList<T> datas){
		mActivity = activity;
		mListDatas = datas;
	}
	
	@Override
	public int getCount() {
		if(mListDatas!=null && mActivity!=null){
			return mListDatas.size();
		}
		return 0;
	}

	@Override
	public T getItem(int arg0) {
		return mListDatas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

}
