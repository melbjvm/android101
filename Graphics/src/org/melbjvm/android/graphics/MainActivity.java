package org.melbjvm.android.graphics;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Zaki Anwar Hamdani
 * 
 *         an <code>android.app.Activity</code> that demonstrates use of custom
 *         view.
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Use entire display space
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// request no title
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// create custom view,set as current view and request focus on that.
		CustomView customeView = new CustomView(this);
		setContentView(customeView);
		customeView.requestFocus();

		// Well, the view has moving objects hence needs continuous updates. The
		// threads has an infinite loop, requesting updated display.
		Thread shadingThread = new Thread(customeView);
		shadingThread.start();
	}
}
