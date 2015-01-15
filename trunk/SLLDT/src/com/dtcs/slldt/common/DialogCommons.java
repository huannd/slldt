package com.dtcs.slldt.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.ebookcontact.R;

public class DialogCommons {
	public static Dialog getDialogSent(Context ctx, final OnDialogClickOkListener eventOK) {
		final Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_sent);
		TextView title = (TextView) dialog.findViewById(R.id.dTitle);
		title.setText(R.string.title_out_sms);
		final EditText edtSMS = (EditText) dialog.findViewById(R.id.edtSMS);
		dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.findViewById(R.id.btnSent).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (eventOK != null) {
					eventOK.onOkClick(edtSMS.getText().toString());
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

	public interface OnDialogClickOkListener {
		void onOkClick(Object obj);
	}

}
