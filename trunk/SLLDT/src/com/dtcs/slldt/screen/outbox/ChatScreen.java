package com.dtcs.slldt.screen.outbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dtcs.slldt.model.SMSGroupModel;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.edu.ebookcontact.R;

public class ChatScreen extends EContactFragment {
	private SMSGroupModel groupSMSModel;
	private ChatAdapter mChatAdapter;
	private ListView lvChat;

	public SMSGroupModel getGroupSMSModel() {
		return groupSMSModel;
	}

	public void setGroupSMSModel(SMSGroupModel groupSMSModel) {
		this.groupSMSModel = groupSMSModel;
	}

	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.chat_screen, container, false);
		lvChat = (ListView) v.findViewById(R.id.lvChat);
		mChatAdapter = new ChatAdapter(getActivity(), groupSMSModel.datas);
		lvChat.setAdapter(mChatAdapter);
		return v;
	}

	@Override
	public String getTitle() {
		return groupSMSModel.getPhoneChat();
	}
}
