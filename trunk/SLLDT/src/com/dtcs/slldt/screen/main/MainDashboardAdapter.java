package com.dtcs.slldt.screen.main;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtcs.slldt.common.BaseListAdapter;
import com.dtcs.slldt.model.MainDashboardItem;
import com.dtcs.slldt.widget.ViewHolder;
import com.edu.ebookcontact.R;
import com.google.android.gms.internal.el;

public class MainDashboardAdapter extends BaseListAdapter<MainDashboardItem>{

	public MainDashboardAdapter(Activity activity,
			ArrayList<MainDashboardItem> datas) {
		super(activity, datas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.adapter_main_dashboard, parent,false);
		}
		ImageView imgIcon = ViewHolder.get(convertView, R.id.img_dashboard_icon);
		TextView tvBadgeNumber = ViewHolder.get(convertView, R.id.tv_badge_number);
		TextView txtTitle = ViewHolder.get(convertView, R.id.tv_dashboard_title);
		MainDashboardItem model = getItem(position);
		imgIcon.setImageResource(model.dashboardIcon);
		txtTitle.setText(model.dashboardName);
		if (model.getBadgeNumber()>0) {
			tvBadgeNumber.setText(""+model.getBadgeNumber());
			tvBadgeNumber.setVisibility(View.VISIBLE);
		}else{
			tvBadgeNumber.setVisibility(View.INVISIBLE);
		}
		
		return convertView;
	}

}
