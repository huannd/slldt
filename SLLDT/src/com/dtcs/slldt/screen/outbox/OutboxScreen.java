package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.DialogCommons.OnDialogClickOkListener;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.common.database.DatabaseQueryController;
import com.dtcs.slldt.common.database.MyDatabaseManager;
import com.dtcs.slldt.gcmservice.GCMManagerMessage;
import com.dtcs.slldt.gcmservice.OnGCMNewMessageListener;
import com.dtcs.slldt.model.ContactModel;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSGroupModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.SMSModel.ChatType;
import com.dtcs.slldt.screen.SMSAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.main.MainScreen;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.dtcs.slldt.widget.SegmentedGroup;
import com.edu.ebookcontact.R;

public class OutboxScreen extends EContactFragment implements OnCheckedChangeListener {

	private ListView mListView;
	private Dialog dCreateSMS, dAddContact;
	private SMSGroupAdapter mAdapter;
	private ArrayList<SMSModel> mDatas;
	private ArrayList<SMSModel> mDataAlls;
	private ArrayList<SMSModel> mDataSendeds;
	private ArrayList<SMSModel> mDataReceiveds;
	private ArrayList<SMSGroupModel> mChatDatas;

	private ArrayList<ContactModel> lstContacts;
	private ContactAdapter mContactAdapter;

	private LinearLayout header_outbox;
	private ChatType currentChatType = ChatType.CHAT_ALL;
	private SegmentedGroup vSegmentedGroup;

	private ImageView btnSMSContact;

	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_outbox, container, false);

		vSegmentedGroup = (SegmentedGroup) v.findViewById(R.id.segmentedGroup);
		vSegmentedGroup.setOnCheckedChangeListener(this);
		header_outbox = (LinearLayout) v.findViewById(R.id.header_outbox);
		btnSMSContact = (ImageView) v.findViewById(R.id.btnSMSContact);
		btnSMSContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentChatType == ChatType.CHAT_ALL) {
					ChatScreen cs = new ChatScreen();
					switchContent(cs, true);
				} else if (currentChatType == ChatType.CONTACT) {
					if (dAddContact == null) {
						dAddContact = DialogCommons.getDialogAddContact(getActivity(), mDialogClickOkListener);
					}
					dAddContact.show();
				}

			}
		});
		mListView = (ListView) v.findViewById(R.id.lv_outbox);
		initOutbox();
		GCMManagerMessage.getInstance().addDelegateListener(mGcmNewMessageListener);
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		GCMManagerMessage.getInstance().removeDelegateListener(mGcmNewMessageListener);
	}

	private OnGCMNewMessageListener mGcmNewMessageListener = new OnGCMNewMessageListener() {

		@Override
		public void onNewMessage(long id) {
			Log.i("CHAT", "has new CHAT");
		}
	};

	private OnDialogClickOkListener mDialogClickOkListener = new OnDialogClickOkListener() {

		@Override
		public void onOkClick(Object obj) {
			if (obj instanceof SMSModel) {
				SMSModel model = ((SMSModel) obj);
				showLoading("Đang gửi tin...");
				SMSGatewayWebservice.sendSMS(model.SDT_Nhan, model.Noi_Dung, new WebserviceTaskListener<ResultModel>() {

					@Override
					public void onTaskComplete(ResultModel ob, ResultModel result) {
						hideLoading();
						if (result != null && result.getErrorCode() == 0) {
							Toast.makeText(getActivity(), "Gửi tin nhắn thành công.", Toast.LENGTH_SHORT).show();
							sync();
						} else {
							Toast.makeText(getActivity(), "Gửi tin thất bại.Vui lòng gửi lại sau", Toast.LENGTH_SHORT)
									.show();
						}
					}
				});
			} else if (obj instanceof ContactModel) {
				ContactModel ctm = ((ContactModel) obj);
				long res = DatabaseQueryController.getInstance().insertNewContact(
						MyDatabaseManager.getInstance(getActivity()), ctm);
				if (res > 0) {
					Toast.makeText(getActivity(), "Thêm danh bạ thành công!", Toast.LENGTH_SHORT).show();
					initContact();
				}
			}
		}
	};

	private void initOutbox() {
		mChatDatas = new ArrayList<SMSGroupModel>();
		mDatas = new ArrayList<SMSModel>();
		mDataAlls = new ArrayList<SMSModel>();
		mDataReceiveds = new ArrayList<SMSModel>();
		mDataSendeds = new ArrayList<SMSModel>();
		// mAdapter = new OutBoxAdapter(getActivity(), mDatas);
		mAdapter = new SMSGroupAdapter(getActivity(), mChatDatas);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Dialog dialogSMSContent =
				// DialogCommons.getDialogShowSMS(getActivity(),
				// mChatDatas.get(position).getMessage(),
				// null);
				// dialogSMSContent.show();
				SMSGroupModel groupSMSModel = mChatDatas.get(position);
				/** show chat screen with list sms **/
				ChatScreen cs = new ChatScreen();
				// cs.setGroupSMSModel(groupSMSModel);
				cs.setGuestPhoneNumber(groupSMSModel.getPhoneChat());
				switchContent(cs, true);
			}
		});
		sync();
	}

	private void filterDatas(ArrayList<SMSModel> datas) {
		// if (datas.size() > 0) {
		// mDataAlls.clear();
		// mDataReceiveds.clear();
		// mDataSendeds.clear();
		// Collections.reverse(datas);
		// String phoneNumber =
		// UserInfoStoreManager.getInstance().getPhoneNumber();
		// for (SMSModel smsModel : datas) {
		// mDataAlls.add(smsModel);
		// if (smsModel.SDT_Gui != null && smsModel.SDT_Gui.equals(phoneNumber))
		// {
		// mDataSendeds.add(smsModel);
		// }
		// if (smsModel.SDT_Nhan != null &&
		// smsModel.SDT_Nhan.equals(phoneNumber)) {
		// mDataReceiveds.add(smsModel);
		// }
		// }
		// }

		if (datas.size() > 0) {
			Collections.reverse(datas);
			HashMap<String, ArrayList<SMSModel>> hashMapSMS = new HashMap<String, ArrayList<SMSModel>>();
			ArrayList<String> listKey = new ArrayList<String>();
			for (SMSModel smsModel : datas) {
				String phoneKey = smsModel.getPhoneChat();
				if (hashMapSMS.containsKey(phoneKey)) {
					ArrayList<SMSModel> smsList = hashMapSMS.get(phoneKey);
					smsList.add(smsModel);
				} else {
					listKey.add(phoneKey);
					ArrayList<SMSModel> smsList = new ArrayList<SMSModel>();
					smsList.add(smsModel);
					hashMapSMS.put(phoneKey, smsList);
				}
			}

			for (String key : listKey) {
				ArrayList<SMSModel> list = hashMapSMS.get(key);
				if (list != null) {
					SMSGroupModel model = new SMSGroupModel();
					model.datas = list;
					mChatDatas.add(model);
				}
			}
		}
	}

	private void switchChat() {
		mDatas.clear();
		switch (currentChatType) {
		case CHAT_ALL:
			mListView.setAdapter(mAdapter);
			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					SMSGroupModel groupSMSModel = mChatDatas.get(position);
					/** show chat screen with list sms **/
					ChatScreen cs = new ChatScreen();
					// cs.setGroupSMSModel(groupSMSModel);
					cs.setGuestPhoneNumber(groupSMSModel.getPhoneChat());
					switchContent(cs, true);
				}
			});
			mAdapter.notifyDataSetChanged();
			btnSMSContact.setImageResource(R.drawable.ic_compose);
			break;
		// case CHAT_SEND:
		// mDatas.addAll(mDataSendeds);
		// break;
		// case CHAT_RECEIVE:
		// mDatas.addAll(mDataReceiveds);
		// break;
		case CONTACT:
			initContact();
			break;
		default:
			mDatas.addAll(mDataAlls);
			break;
		}
	}

	private void initContact() {
		lstContacts = (ArrayList<ContactModel>) DatabaseQueryController.getInstance().getContactList(
				MyDatabaseManager.getInstance(getActivity()));
		if (lstContacts != null || lstContacts.size() == 0) {
			mContactAdapter = new ContactAdapter(getActivity(), lstContacts);
			mListView.setAdapter(mContactAdapter);
		}
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ContactModel ctm = lstContacts.get(position);
				// SMSGroupModel groupSMSModel = null;
				// if (mChatDatas != null) {
				// for (int i = 0; i < mChatDatas.size(); i++) {
				// SMSGroupModel obj = mChatDatas.get(i);
				// if (obj.getPhoneChat().equals(ctm.getPhoneNum())
				// || PhoneNumberUtils.compare(obj.getPhoneChat(),
				// ctm.getPhoneNum())) {
				// groupSMSModel = obj;
				// break;
				// }
				// }
				// }

				/** show chat screen with list sms **/
				ChatScreen cs = new ChatScreen();
				// cs.setGroupSMSModel(groupSMSModel);
				cs.setGuestPhoneNumber(ctm.getPhoneNum());
				switchContent(cs, true);
			}
		});
		btnSMSContact.setImageResource(R.drawable.add_user);
	}

	@Override
	public void sync() {
		// showLoading();
		// SMSGatewayWebservice.getListSmsSended(new
		// WebserviceTaskListener<ArrayList<SMSModel>>() {
		//
		// @Override
		// public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel
		// result) {
		// if (ob != null) {
		// filterDatas(ob);
		// switchChat();
		// }
		// hideLoading();
		// }
		// });

		showLoading();
		SMSGatewayWebservice.getListSmsChat(new WebserviceTaskListener<ArrayList<SMSModel>>() {

			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob != null) {
					filterDatas(ob);
					switchChat();
				}
				hideLoading();
			}
		});
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_outbox);
	}

	class OutBoxAdapter extends SMSAdapter {

		public OutBoxAdapter(Activity activity, ArrayList<SMSModel> datas) {
			super(activity, datas);
		}

		@Override
		protected String getPhoneAtPosition(int pos) {
			SMSModel model = getItem(pos);
			if (currentChatType == ChatType.CHAT_SEND) {
				if (model.SDT_Nhan != null && !model.SDT_Nhan.trim().equals("")) {
					return "To: " + model.SDT_Nhan;
				}
			} else if (currentChatType == ChatType.CHAT_RECEIVE) {
				if (model.SDT_Gui != null && !model.SDT_Gui.trim().equals("")) {
					return "From: " + model.SDT_Gui;
				}
			}
			return "";
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rdOutbox:
			currentChatType = ChatType.CHAT_ALL;
			break;
		case R.id.rdContact:
			currentChatType = ChatType.CONTACT;
			break;
		default:
			break;
		}
		switchChat();
	}

	@Override
	public void onBackPress() {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		if (fm.getBackStackEntryCount() > 1) {
			super.onBackPress();
		} else {
			MainScreen main = new MainScreen();
			main.setShowListStudent(UserInfoStoreManager.getInstance().getCurrentStudentId() <= 0);
			switchContent(main, false);
		}
	}
}
