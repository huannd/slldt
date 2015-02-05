package com.dtcs.slldt.common;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtcs.slldt.common.database.DatabaseQueryController;
import com.dtcs.slldt.common.database.MyDatabaseManager;
import com.dtcs.slldt.model.ContactModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.screen.outbox.ContactAdapter;
import com.edu.ebookcontact.R;

public class DialogCommons {
	public static Dialog getDialogSent(final Context ctx, final OnDialogClickOkListener eventOK) {
		final Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_sent);
		TextView title = (TextView) dialog.findViewById(R.id.dTitle);
		title.setText(R.string.title_out_sms);
		final EditText edtSMS = (EditText) dialog.findViewById(R.id.edtSMS);
		final EditText edtPhone = (EditText) dialog.findViewById(R.id.edtPhone);
		dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.findViewById(R.id.btnSent).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String phone = edtPhone.getText().toString();
				String content = edtSMS.getText().toString();
				if (phone == null || phone.trim().equals("")) {
					Toast.makeText(ctx, "Hãy nhập số điện thoại.", Toast.LENGTH_SHORT).show();
					return;
				}
				if (content == null || content.trim().equals("")) {
					Toast.makeText(ctx, "Hãy nhập nội dung tin nhắn.", Toast.LENGTH_SHORT).show();
					return;
				}
				if (eventOK != null) {
					SMSModel model = new SMSModel();
					model.SDT_Nhan = phone;
					model.Noi_Dung = content;
					eventOK.onOkClick(model);
				}
				dialog.dismiss();
			}
		});
		return dialog;
	}

	public static Dialog getDialogShowSMS(Context ctx, String content, final OnDialogClickOkListener eventOK) {
		final Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_received);
		TextView title = (TextView) dialog.findViewById(R.id.dTitle);
		title.setText(R.string.title_in_sms);
		TextView tvSMS = (TextView) dialog.findViewById(R.id.tvSMS);
		tvSMS.setText(content);
		dialog.findViewById(R.id.btnClose).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (eventOK != null) {
					// nothing..
				}
				dialog.dismiss();
			}
		});
		return dialog;
	}

	public static Dialog getDialogChangePassword(final Context ctx, final OnDialogClickOkListener eventOK) {
		final Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_change_password);
		final EditText edtCurrentPass = (EditText) dialog.findViewById(R.id.edt_current_pass);
		final EditText edtNewPass = (EditText) dialog.findViewById(R.id.edt_new_pass);
		final EditText edtConfirmNewPass = (EditText) dialog.findViewById(R.id.edt_confirm_new_pass);
		dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.findViewById(R.id.btnChange).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String currentPass = edtCurrentPass.getText().toString();
				String newPass = edtNewPass.getText().toString();
				String newPassConfirm = edtConfirmNewPass.getText().toString();
				if (currentPass == null || currentPass.trim().equals("")) {
					Toast.makeText(ctx, "Chưa nhập mật khẩu hiện tại.", Toast.LENGTH_SHORT).show();
					return;
				}
				String password = SessionStore.getInstance().getPassword();
				if (!currentPass.trim().equals(password)) {
					Toast.makeText(ctx, "Mật khẩu hiện tại chưa đúng.", Toast.LENGTH_SHORT).show();
					return;
				}

				if (newPass == null || newPass.trim().equals("")) {
					Toast.makeText(ctx, "Chưa nhập mật khẩu mới.", Toast.LENGTH_SHORT).show();
					return;
				}

				if (newPassConfirm == null || newPassConfirm.trim().equals("")) {
					Toast.makeText(ctx, "Chưa xác nhận mật khẩu mới.", Toast.LENGTH_SHORT).show();
					return;
				}

				if (!newPass.trim().equals(newPassConfirm.trim())) {
					Toast.makeText(ctx, "Xác nhận mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
					return;
				}

				if (eventOK != null) {
					eventOK.onOkClick(newPass);
				}
				dialog.dismiss();
			}
		});
		return dialog;
	}

	public static Dialog getDialogAddContact(final Context ctx, final OnDialogClickOkListener eventOK) {
		final Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.add_contact);
		final EditText edtName = (EditText) dialog.findViewById(R.id.edtContactName);
		final EditText edtPhone = (EditText) dialog.findViewById(R.id.edtPhone);
		final EditText edtDes = (EditText) dialog.findViewById(R.id.edtDes);
		dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.findViewById(R.id.btnAdd).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String phone = edtPhone.getText().toString();
				String name = edtName.getText().toString();
				String des = edtDes.getText().toString();
				if (name == null || name.trim().equals("")) {
					Toast.makeText(ctx, "Hãy nhập Tên danh bạ.", Toast.LENGTH_SHORT).show();
					return;
				}
				if (phone == null || phone.trim().equals("")) {
					Toast.makeText(ctx, "Hãy nhập số điện thoại.", Toast.LENGTH_SHORT).show();
					return;
				}
				if (eventOK != null) {
					ContactModel model = new ContactModel();
					model.setPhoneNum(phone);
					model.setName(name);
					model.setDescriptions(des);
					eventOK.onOkClick(model);
				}
				dialog.dismiss();
			}
		});
		return dialog;
	}

	public static Dialog getDialogContact(final Context ctx, final OnDialogClickOkListener eventOK) {
		final Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.contact_dialog);
		final ListView lv = (ListView) dialog.findViewById(R.id.lvContactDialog);
		dialog.findViewById(R.id.btnCloseContact).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		final ArrayList<ContactModel> lstContacts = (ArrayList<ContactModel>) DatabaseQueryController.getInstance()
				.getContactList(MyDatabaseManager.getInstance(ctx));
		if (lstContacts != null || lstContacts.size() == 0) {
			ContactAdapter mContactAdapter = new ContactAdapter(ctx, lstContacts);
			lv.setAdapter(mContactAdapter);
		}
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (eventOK != null){
					eventOK.onOkClick(lstContacts.get(position));
				}
				dialog.dismiss();
			}
		});

		// .setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// String phone = edtPhone.getText().toString();
		// String content = edtSMS.getText().toString();
		// if (phone == null || phone.trim().equals("")) {
		// Toast.makeText(ctx, "Hãy nhập số điện thoại.",
		// Toast.LENGTH_SHORT).show();
		// return;
		// }
		// if (content == null || content.trim().equals("")) {
		// Toast.makeText(ctx, "Hãy nhập nội dung tin nhắn.",
		// Toast.LENGTH_SHORT).show();
		// return;
		// }
		// if (eventOK != null) {
		// SMSModel model = new SMSModel();
		// model.SDT_Nhan = phone;
		// model.Noi_Dung = content;
		// eventOK.onOkClick(model);
		// }
		// dialog.dismiss();
		// }
		// });
		return dialog;
	}

	public interface OnDialogClickOkListener {
		void onOkClick(Object obj);
	}

}
