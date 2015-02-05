package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.DialogCommons.OnDialogClickOkListener;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ContactModel;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class ChatScreen extends EContactFragment implements OnClickListener {
	// private SMSGroupModel groupSMSModel;
	private String guestPhoneNumber;
	private ChatAdapter mChatAdapter;
	private ListView lvChat;
	private Button send;
	private EditText inSMS;
	private ArrayList<SMSModel> chats;
	private String num;
	private EditText edtPhone;

	// public SMSGroupModel getGroupSMSModel() {
	// return groupSMSModel;
	// }
	//
	// public void setGroupSMSModel(SMSGroupModel groupSMSModel) {
	// this.groupSMSModel = groupSMSModel;
	// }

	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}

	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.chat_screen, container, false);
		lvChat = (ListView) v.findViewById(R.id.lvChat);
		init();
		send = (Button) v.findViewById(R.id.send);
		inSMS = (EditText) v.findViewById(R.id.edtSMS);
		send.setOnClickListener(this);

		if (guestPhoneNumber == null) {
			edtPhone = mHeaderBarFactory.getHeader().setVisibleContactIcon(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Dialog contact = DialogCommons.getDialogContact(getActivity(), new OnDialogClickOkListener() {
						
						@Override
						public void onOkClick(Object obj) {
							ContactModel ctm = (ContactModel)obj;
							setGuestPhoneNumber(ctm.getPhoneNum());
							edtPhone.setText(ctm.getPhoneNum());
						}
					});
					contact.show();
				}
			});
			edtPhone.setVisibility(View.VISIBLE);
		}

		return v;
	}

	private void init() {
		if (guestPhoneNumber == null){
			chats = new ArrayList<SMSModel>();
			mChatAdapter = new ChatAdapter(getActivity(), chats);
			lvChat.setAdapter(mChatAdapter);
			return;
		}
		showLoading();
		SMSGatewayWebservice.getListSmsChatWithPhoneNumber(guestPhoneNumber,
				new WebserviceTaskListener<ArrayList<SMSModel>>() {

					@Override
					public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
						if (ob != null) {
							chats = ob;
							mChatAdapter = new ChatAdapter(getActivity(), ob);
							lvChat.setAdapter(mChatAdapter);
						}
						hideLoading();
					}
				});
	}

	@Override
	public String getTitle() {
		return guestPhoneNumber == null ? "" : guestPhoneNumber;
		// if (groupSMSModel == null) {
		// return "Trò chuyện";
		// }
		// return groupSMSModel.getPhoneChat();
	}

	@Override
	public void onClick(View v) {
		if (inSMS.getText().toString().trim().equals("")) {
			Toast.makeText(getActivity(), "Nhập Trò chuyện.", Toast.LENGTH_SHORT).show();
		} else {
			showLoading("Đang gửi tin...");

			SMSModel sms = new SMSModel();
			sms.Ngay_Duyet = String.valueOf(Calendar.getInstance().getTimeInMillis());
			sms.SDT_Gui = UserInfoStoreManager.getInstance().getPhoneNumber();
			sms.SDT_Nhan = guestPhoneNumber;
			sms.Noi_Dung = inSMS.getText().toString();
			chats.add(sms);
			mChatAdapter.notifyDataSetChanged();
			String phone = guestPhoneNumber == null ? edtPhone.getText().toString().trim() : guestPhoneNumber;
			SMSGatewayWebservice.sendSMS(phone, inSMS.getText().toString(), new WebserviceTaskListener<ResultModel>() {

				@Override
				public void onTaskComplete(ResultModel ob, ResultModel result) {
					hideLoading();
					if (result != null && result.getErrorCode() == 0) {
						Toast.makeText(getActivity(), "Gửi tin nhắn thành công.", Toast.LENGTH_SHORT).show();

					} else {
						Toast.makeText(getActivity(), "Gửi tin thất bại.Vui lòng gửi lại sau", Toast.LENGTH_SHORT)
								.show();
					}
				}
			});
			inSMS.setText("");
		}
	}
}
