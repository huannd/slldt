package com.dtcs.slldt.screen.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.ebookcontact.R;

public abstract class BaseFragment extends Fragment {

	/** The m view screen container. */
	protected View mViewScreenContainer;
	
	/** The m content view. */
	protected View mContentView;
	
	/** The m header container. */
	protected ViewGroup mHeaderContainer;
	
	/** The m content container. */
	protected ViewGroup mContentContainer;

	public String pageTitle;
	
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// --------------Init Screen--------------
		// ----init contanier view
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		this.mViewScreenContainer = inflater.inflate(R.layout.base_screen, null);

		mHeaderContainer = (ViewGroup) this.mViewScreenContainer.findViewById(R.id.layout_header);
		mContentContainer = (ViewGroup) this.mViewScreenContainer.findViewById(R.id.layout_content);
		
		// ----add header view
		View headerView = this.onCreateHeaderView(inflater, mHeaderContainer);
		if (headerView != null) {
			mHeaderContainer.addView(headerView);
		}

		// ----add content view
		this.mContentView = this.onCreateContentView(inflater, mContentContainer);
		if (this.mContentView != null) {
			mContentContainer.addView(this.mContentView);
		}
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (this.mViewScreenContainer.getParent() != null) {
			((ViewGroup) this.mViewScreenContainer.getParent()).removeView(this.mViewScreenContainer);
		}

		return (View) this.mViewScreenContainer;
	}


	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy() {
		this.mHeaderContainer.removeAllViews();
		this.mContentContainer.removeAllViews();
		this.mContentView = null;
		this.mHeaderContainer = null;
		this.mContentContainer = null;
		this.mViewScreenContainer = null;
		super.onDestroy();
	}

	public ViewGroup getContentView(){
		return mContentContainer;
	}
	/**
	 * On create header view.
	 *
	 * @param inflater the inflater
	 * @param container the container
	 * @return the view
	 */
	protected View onCreateHeaderView(LayoutInflater inflater, ViewGroup container){
		return null;
	}
	
	/**
	 * On create content view.
	 *
	 * @param inflater the inflater
	 * @param container the container
	 * @return the view
	 */
	protected abstract View onCreateContentView(LayoutInflater inflater, ViewGroup container);
}
