package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.dtcs.slldt.common.BaseListAdapter;
import com.dtcs.slldt.model.SMSGroupModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.widget.ViewHolder;
import com.edu.ebookcontact.R;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SMSGroupAdapter extends BaseListAdapter<SMSGroupModel>{

	

	public SMSGroupAdapter(Activity activity, ArrayList<SMSGroupModel> datas) {
		super(activity, datas);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.adapter_sms, parent,false);
		}
		TextView txtStt = ViewHolder.get(convertView, R.id.tv_sms_stt);
		TextView txtTime = ViewHolder.get(convertView, R.id.tv_sms_time);
		TextView txtPhone = ViewHolder.get(convertView, R.id.tv_phone);
		TextView txtContent = ViewHolder.get(convertView,R.id.tv_sms_content);
		SMSGroupModel groupModel = getItem(position);
		if (groupModel != null) {
			txtStt.setText(""+(position+1));
			txtPhone.setText(groupModel.getPhoneChat());
			Date smsTime = groupModel.getTime();
			if (smsTime!=null) {
				txtTime.setText(DateFormat.format("dd-MM-yyyy", smsTime));
			}
			txtContent.setText(groupModel.getMessage());
		}
		return convertView;
	}

}
