package com.dtcs.slldt.screen;

import java.util.ArrayList;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtcs.slldt.common.BaseListAdapter;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.widget.ViewHolder;
import com.edu.ebookcontact.R;

public class StudentAdapter extends BaseListAdapter<StudentModel>{

	public StudentAdapter(Activity activity, ArrayList<StudentModel> datas) {
		super(activity, datas);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.adapter_student, parent,false);
		}
		TextView txtName = ViewHolder.get(convertView, R.id.tv_student_name);
		TextView txtClass = ViewHolder.get(convertView, R.id.tv_student_class);
		TextView txtSchool = ViewHolder.get(convertView,R.id.tv_student_school);
		StudentModel model = getItem(position);
		if (model!=null) {
			txtName.setText(Html.fromHtml("<i><b> Họ tên: </i></b>" + model.Hoten_HocSinh));
			txtClass.setText(Html.fromHtml("<i><b> Họ tên: </i></b>" + model.Ten_Lop));
			txtSchool.setText(Html.fromHtml("<i><b> Họ tên: </i></b>" + model.Ten_Truong));
		}
		return convertView;
	}

}
