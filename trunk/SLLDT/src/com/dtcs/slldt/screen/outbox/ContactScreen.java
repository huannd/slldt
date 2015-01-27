package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dtcs.slldt.common.database.DatabaseQueryController;
import com.dtcs.slldt.common.database.MyDatabaseManager;
import com.dtcs.slldt.model.ContactModel;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.edu.ebookcontact.R;

public class ContactScreen extends EContactFragment {
	private ArrayList<ContactModel> lstContacts;
	private ContactAdapter mContactAdapter;
	private ListView lvContact;

	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.contact_list, container, false);
		lvContact = (ListView) v.findViewById(R.id.lvContact);
		lstContacts = (ArrayList<ContactModel>) DatabaseQueryController.getInstance().getContactList(
				MyDatabaseManager.getInstance(getActivity()));
		if (lstContacts != null || lstContacts.size() == 0) {
			mContactAdapter = new ContactAdapter(getActivity(), lstContacts);
			lvContact.setAdapter(mContactAdapter);
		}
		return v;
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_contact);
	}
}
