package pctest.apps.sarbox;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * This fragment opens the targets page
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_Target extends Fragment {

	Button button_622092;
	Button button_622091;
	Button button_15282013;
	
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
		
		if (isLocked) {
			final View view = inflater.inflate(R.layout.fragment_loginlock,
					container, false);
			return view;
		} else {
			final View view = inflater.inflate(R.layout.fragment_target,
					container, false);

			button_622091 = (Button) view.findViewById(R.id.target_button_IEC622091);

			button_622091.setOnClickListener(new OnClickListener() {

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
								getActivity(), "target_622091"));
					} catch (IOException e) {
						// TODO: generate better error messages
						e.printStackTrace();
					}

					dialog.show();
				}
			});
			
			button_622092 = (Button) view.findViewById(R.id.target_button_IEC622092);

			button_622092.setOnClickListener(new OnClickListener() {

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
								getActivity(), "target_622092"));
					} catch (IOException e) {
						// TODO: generate better error messages
						e.printStackTrace();
					}

					dialog.show();
				}
			});
			
			button_15282013 = (Button) view.findViewById(R.id.target_button_IEEE15282013);

			button_15282013.setOnClickListener(new OnClickListener() {

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
								getActivity(), "target_15282013"));
					} catch (IOException e) {
						// TODO: generate better error messages
						e.printStackTrace();
					}

					dialog.show();
				}
			});
			return view;
		}
	}
}
