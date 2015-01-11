package com.dtcs.slldt.screen.base;

import com.dtcs.slldt.activity.BaseFragmentActivity;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.ISyncListener;
import com.dtcs.slldt.screen.SyncManager;
import com.dtcs.slldt.screen.header.HeaderBarFactory;
import com.dtcs.slldt.screen.header.IHeaderBar;
import com.google.android.gms.internal.el;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class EContactFragment extends BaseTaskFragment implements IHeaderBar{
	
	protected HeaderBarFactory mHeaderBarFactory;
	private ProgressDialog mProgressDialog;
	
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
	
	@Override
	public void sync() {
	}
	
	public void showLoading(String message){
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(getActivity());
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.setCancelable(false);
		}
		if (message != null) {
			mProgressDialog.setMessage(message);
		}else{
			mProgressDialog.setMessage("tải dữ liệu...");
		}
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
	}
	
	public void showLoading(){
		showLoading(null);
	}
	
	public void hideLoading(){
		if (mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}
	
	public void switchContent(Fragment frag,boolean isAddToBackStack){
		if (getActivity() instanceof BaseFragmentActivity) {
			((BaseFragmentActivity)getActivity()).switchContent(frag, isAddToBackStack);
		}
	}
}
