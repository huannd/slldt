package com.dtcs.slldt.screen.base;

import com.dtcs.slldt.activity.BaseFragmentActivity;
import com.dtcs.slldt.screen.header.HeaderBarFactory;
import com.dtcs.slldt.screen.header.IHeaderBar;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class EContactFragment extends BaseTaskFragment implements IHeaderBar{
	
	protected HeaderBarFactory mHeaderBarFactory;
	
	@Override
	protected View onCreateHeaderView(LayoutInflater inflater,
			ViewGroup container) {
		mHeaderBarFactory = new HeaderBarFactory(this);
		return mHeaderBarFactory.onCreateHeaderView(inflater, container, getHeaderBarType());
	}
	
	protected HeaderType getHeaderBarType() {
		return HeaderType.DEFAULT;
	}
	
	@Override
	public void onBackPress() {
		getActivity().onBackPressed();
	}
	
	@Override
	public String getTitle() {
		return null;
	}
	
	public void switchContent(Fragment frag,boolean isAddToBackStack){
		if (getActivity() instanceof BaseFragmentActivity) {
			((BaseFragmentActivity)getActivity()).switchContent(frag, isAddToBackStack);
		}
	}
}
