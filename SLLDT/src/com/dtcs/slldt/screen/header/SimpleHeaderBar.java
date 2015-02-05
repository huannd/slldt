package com.dtcs.slldt.screen.header;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtcs.slldt.screen.main.MainScreen;
import com.dtcs.slldt.screen.outbox.ChatScreen;
import com.dtcs.slldt.screen.outbox.OutboxScreen;
import com.edu.ebookcontact.R;

public class SimpleHeaderBar {

	private Fragment mFrag;
	private ImageView imgRight;
	private EditText edtPhone;
	private TextView tvTitle;

	public SimpleHeaderBar(Fragment frag) {
		mFrag = frag;
	}

	public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
		View root = inflater.inflate(R.layout.header_bar_simple, container);
		root.findViewById(R.id.btnBack).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mFrag instanceof IHeaderBar) {
					((IHeaderBar) mFrag).onBackPress();
				}
			}
		});
		imgRight = (ImageView) root.findViewById(R.id.main_btn_switch);
		edtPhone = (EditText) root.findViewById(R.id.edtChatPhone);
		imgRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mFrag instanceof IHeaderBar) {
					((IHeaderBar) mFrag).sync();
				}
			}
		});
		if (!(mFrag instanceof MainScreen) && !(mFrag instanceof OutboxScreen)) {
			imgRight.setVisibility(View.INVISIBLE);
		}
		if (mFrag instanceof ChatScreen) {
			imgRight.setImageResource(R.drawable.plus);
		}
		if (mFrag instanceof IHeaderBar) {
			tvTitle = (TextView) root.findViewById(R.id.title);
			tvTitle.setText(Html.fromHtml(((IHeaderBar) mFrag).getTitle()));
		}
		return null;
	}

	public EditText setVisibleContactIcon(OnClickListener onclick) {
		imgRight.setVisibility(View.VISIBLE);
		imgRight.setOnClickListener(onclick);
		tvTitle.setVisibility(View.GONE);
		return edtPhone;
	}
}
