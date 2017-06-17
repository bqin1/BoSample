package pctest.apps.sarbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This fragment is an under construction message
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_UnderConstruction extends Fragment{
   @Override
   public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    
      return inflater.inflate(
              R.layout.fragment_underconstruction, container, false);
   }
}
