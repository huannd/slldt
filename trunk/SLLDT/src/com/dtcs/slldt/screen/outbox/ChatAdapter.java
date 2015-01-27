package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.SMSModel;
import com.edu.ebookcontact.R;

public class ChatAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<SMSModel> mMessages;

	public ChatAdapter(Context context, ArrayList<SMSModel> messages) {
		super();
		this.mContext = context;
		this.mMessages = messages;
	}

	@Override
	public int getCount() {
		return mMessages.size();
	}

	@Override
	public Object getItem(int position) {
		return mMessages.get(position);
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SMSModel message = (SMSModel) this.getItem(position);

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chat, parent, false);
			holder.message = (TextView) convertView.findViewById(R.id.message_text);
			holder.background = (LinearLayout) convertView.findViewById(R.id.background);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.message.setText(message.Noi_Dung);
		holder.time.setText(DateFormat.format("dd-MM-yyyy", message.getTime()));

		LayoutParams lp = (LayoutParams) holder.message.getLayoutParams();
		if (message.SDT_Gui.equalsIgnoreCase(UserInfoStoreManager.getInstance().getPhoneNumber())
				|| PhoneNumberUtils.compare(message.SDT_Gui, UserInfoStoreManager.getInstance().getPhoneNumber())) {
			holder.background.setBackgroundResource(R.drawable.speech_bubble_green);
			lp.gravity = Gravity.RIGHT;
		} else {
			holder.background.setBackgroundResource(R.drawable.speech_bubble_orange);
			lp.gravity = Gravity.LEFT;
		}
		holder.background.setLayoutParams(lp);
		holder.message.setTextColor(R.color.textColor);
		return convertView;
	}

	private static class ViewHolder {
		LinearLayout background;
		TextView time;
		TextView message;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
