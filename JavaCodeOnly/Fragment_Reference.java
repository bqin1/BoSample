package pctest.apps.sarbox;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Fragment_Reference extends Fragment {

	Button button_datarate;
	Button button_bicouplersetup;
	Button button_datachecklist;
	Button button_cmwrun;
	Button button_methanol;
	Button button_freqchannel;

	Uri cmwrunURIPath;
	Uri methanolURIPath;

	boolean isLocked;

	public static Drawable getAssetImage(Context context, String filename)
			throws IOException {
		AssetManager assets = context.getResources().getAssets();
		InputStream buffer = new BufferedInputStream((assets.open(filename
				+ ".png")));
		Bitmap bitmap = BitmapFactory.decodeStream(buffer);
		return new BitmapDrawable(context.getResources(), bitmap); 
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		isLocked = Util_GlobalHandler.getGlobalHandler().getIsLocked();
		
		fileRelated();
		
		if (isLocked) {
			final View view = inflater.inflate(R.layout.fragment_loginlock,
					container, false);
			return view;
		} else {
			final View view = inflater.inflate(R.layout.fragment_reference,
					container, false);

			button_bicouplersetup = (Button) view
					.findViewById(R.id.reference_button_bicoupersetup);

			button_bicouplersetup.setOnClickListener(new OnClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View arg0) {
					Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.misc_imagedialogue);
					dialog.setCanceledOnTouchOutside(true);

					ImageView image = (ImageView) dialog
							.findViewById(R.id.imagedialogue);

					try {
						image.setBackgroundDrawable(getAssetImage(
								getActivity(), "reference_bicouplersetup"));
					} catch (IOException e) {
						e.printStackTrace();
					}

					dialog.show();
				}
			});

			button_datarate = (Button) view
					.findViewById(R.id.reference_button_datarate);

			button_datarate.setOnClickListener(new OnClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View arg0) {
					Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.misc_imagedialogue);
					dialog.setCanceledOnTouchOutside(true);

					ImageView image = (ImageView) dialog
							.findViewById(R.id.imagedialogue);

					try {
						image.setBackgroundDrawable(getAssetImage(
								getActivity(), "reference_datarate"));
					} catch (IOException e) {
						e.printStackTrace();
					}

					dialog.show();
				}
			});

			button_datachecklist = (Button) view
					.findViewById(R.id.reference_button_datachecklist);

			button_datachecklist.setOnClickListener(new OnClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View arg0) {
					Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.misc_imagedialogue);
					dialog.setCanceledOnTouchOutside(true);

					ImageView image = (ImageView) dialog
							.findViewById(R.id.imagedialogue);

					try {
						image.setBackgroundDrawable(getAssetImage(
								getActivity(), "reference_datachecklist"));
					} catch (IOException e) {
						e.printStackTrace();
					}

					dialog.show();
				}
			});

			button_cmwrun = (Button) view
					.findViewById(R.id.reference_button_cmwrun);

			button_cmwrun.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(cmwrunURIPath, "application/pdf");
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});
			
			button_methanol = (Button) view
					.findViewById(R.id.reference_button_methanol);

			button_methanol.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(methanolURIPath, "application/pdf");
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});

			button_freqchannel = (Button) view
					.findViewById(R.id.reference_button_freqchannel);

			button_freqchannel.setOnClickListener(new OnClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View arg0) {
					Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.misc_imagedialogue);
					dialog.setCanceledOnTouchOutside(true);

					ImageView image = (ImageView) dialog
							.findViewById(R.id.imagedialogue);

					try {
						image.setBackgroundDrawable(getAssetImage(
								getActivity(), "reference_freqchannel"));
					} catch (IOException e) {
						e.printStackTrace();
					}

					dialog.show();
				}
			});


			return view;
		}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}
	
	public void fileRelated()
	{
		String cmwrunFilePath = Environment.getExternalStorageDirectory().toString()+"/cmwrun.pdf";
		String methanolFilePath = Environment.getExternalStorageDirectory().toString()+"/methanol.pdf";
		 
		try {
			copyFile(getResources().openRawResource(R.raw.cmwrun),
					new FileOutputStream(new File(
							cmwrunFilePath)));
			copyFile(getResources().openRawResource(R.raw.methanol),
					new FileOutputStream(new File(
							methanolFilePath))); 
		} catch (NotFoundException e1) {
			e1.printStackTrace(); 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) { 
			e1.printStackTrace();
		}

		File cmwrunFile = new File(cmwrunFilePath);
		File methanolFile = new File(methanolFilePath);
		cmwrunURIPath = Uri.fromFile(cmwrunFile);
		methanolURIPath = Uri.fromFile(methanolFile);
	}
}
