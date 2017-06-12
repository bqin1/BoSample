package pctest.apps.sarbox;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Fragment_Home extends Fragment {

	Button button_KDB;
	Button button_FCCLookup;
	Button button_ICLookup;
	Button button_LTELookup;
	
	//TODO: REMOVE the locked checks here and put it on sarbox
	boolean isLocked;
	String message_login_required;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_home, container,
				false);
		isLocked = Util_GlobalHandler.getGlobalHandler().getIsLocked();
		message_login_required = Util_GlobalHandler.getGlobalHandler().get_Message_Login_Required();

		button_ICLookup = (Button) view.findViewById(R.id.home_button_ic);

		button_ICLookup.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View arg0) {
				Uri uri = Uri
						.parse("http://www.ic.gc.ca/app/sitt/reltel/srch/nwRdSrch.do?lang=eng");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}

		});

		button_KDB = (Button) view.findViewById(R.id.home_button_kdb);

		button_KDB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Uri uri = Uri
						.parse("https://apps.fcc.gov/oetcf/kdb/reports/GuidedPublicationList.cfm");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}

		});

		button_FCCLookup = (Button) view.findViewById(R.id.home_button_fcc);

		button_FCCLookup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Uri uri = Uri
						.parse("https://apps.fcc.gov/oetcf/eas/reports/GenericSearch.cfm");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}

		});
		
		button_LTELookup = (Button) view.findViewById(R.id.home_button_lte);

		button_LTELookup.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View arg0) {
				Uri uri = Uri
						.parse("http://niviuk.free.fr/lte_band.php");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}

		});

		return view;
	}
}
