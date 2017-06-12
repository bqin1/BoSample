package pctest.apps.sarbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author bo.qin
 * This fragment is an under construction message
 *
 */
public class Fragment_UnderConstruction extends Fragment{
   @Override
   public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    
      return inflater.inflate(
              R.layout.fragment_underconstruction, container, false);
   }
}
