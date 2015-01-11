package com.dtcs.slldt.screen;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtcs.slldt.common.BaseListAdapter;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.widget.ViewHolder;
import com.edu.ebookcontact.R;

public class SMSAdapter extends BaseListAdapter<SMSModel>{

	public static final String[] DAY_NUMB = { "Chủ Nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7" };
	
	public SMSAdapter(Activity activity, ArrayList<SMSModel> datas) {
		super(activity, datas);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.adapter_sms, parent,false);
		}
		TextView txtStt = ViewHolder.get(convertView, R.id.tv_sms_stt);
		TextView txtTime = ViewHolder.get(convertView, R.id.tv_sms_time);
		TextView txtContent = ViewHolder.get(convertView,R.id.tv_sms_content);
		SMSModel model = getItem(position);
		if (model!=null) {
			txtStt.setText(""+(position+1));
			Date smsTime = model.getTime();
			if (smsTime!=null) {
				txtTime.setText(DateFormat.format("dd-MM-yyyy", smsTime));
			}else {
				txtTime.setText(model.Ngay_Duyet);
			}
			txtContent.setText(model.Noi_Dung);
		}
		return convertView;
	}

}
