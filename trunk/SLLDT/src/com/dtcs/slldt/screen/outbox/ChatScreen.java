package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSGroupModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class ChatScreen extends EContactFragment implements OnClickListener {
//	private SMSGroupModel groupSMSModel;
	private String guestPhoneNumber;
	private ChatAdapter mChatAdapter;
	private ListView lvChat;
	private Button send;
	private EditText inSMS;

//	public SMSGroupModel getGroupSMSModel() {
//		return groupSMSModel;
//	}
//
//	public void setGroupSMSModel(SMSGroupModel groupSMSModel) {
//		this.groupSMSModel = groupSMSModel;
//	}

	public void setGuestPhoneNumber(String guestPhoneNumber){
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
		return v;
	}
	
	private void init(){
		if (guestPhoneNumber == null) return;
		showLoading();
		SMSGatewayWebservice.getListSmsChatWithPhoneNumber(guestPhoneNumber, new WebserviceTaskListener<ArrayList<SMSModel>>() {
			
			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob != null) {
					mChatAdapter = new ChatAdapter(getActivity(), ob);
					lvChat.setAdapter(mChatAdapter);
				}
				hideLoading();
			}
		});
	}

	@Override
	public String getTitle() {
		return guestPhoneNumber;
//		if (groupSMSModel == null) {
//			return "Trò chuyện";
//		}
//		return groupSMSModel.getPhoneChat();
	}

	@Override
	public void onClick(View v) {
		if (inSMS.getText().toString().trim().equals("")) {
			Toast.makeText(getActivity(), "Nhập Trò chuyện.", Toast.LENGTH_SHORT).show();
		} else {
			showLoading("Đang gửi tin...");
			SMSGatewayWebservice.sendSMS(guestPhoneNumber, inSMS.getText().toString(),
					new WebserviceTaskListener<ResultModel>() {

						@Override
						public void onTaskComplete(ResultModel ob, ResultModel result) {
							hideLoading();
							if (result != null && result.getErrorCode() == 0) {
								Toast.makeText(getActivity(), "Gửi tin nhắn thành công.", Toast.LENGTH_SHORT).show();
								// sync(); TODO refresh list
							} else {
								Toast.makeText(getActivity(), "Gửi tin thất bại.Vui lòng gửi lại sau",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			inSMS.setText("");
		}
	}
}
