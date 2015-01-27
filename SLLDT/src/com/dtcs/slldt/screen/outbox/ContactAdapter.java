package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dtcs.slldt.model.ContactModel;
import com.edu.ebookcontact.R;

public class ContactAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<ContactModel> mMessages;

	public ContactAdapter(Context context, ArrayList<ContactModel> messages) {
		super();
		this.mContext = context;
		this.mMessages = messages;
	}

	@Override
	public int getCount() {
		if (mMessages == null || mMessages.size() == 0) {
			return 0;
		}
		return mMessages.size();
	}

	@Override
	public Object getItem(int position) {
		if (mMessages == null || mMessages.size() == 0) {
			return null;
		}
		return mMessages.get(position);
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactModel ct = (ContactModel) this.getItem(position);

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_item, parent, false);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.phone = (TextView) convertView.findViewById(R.id.phone);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.name.setText(ct.getName());
		holder.phone.setText(ct.getPhoneNum());
		return convertView;
	}

	private static class ViewHolder {
		TextView name;
		TextView phone;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}