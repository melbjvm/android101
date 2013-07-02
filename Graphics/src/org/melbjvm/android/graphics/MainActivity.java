package org.melbjvm.android.graphics;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		CustomView customeView = new CustomView(this);
		setContentView(customeView);
		customeView.requestFocus();

		Thread shadingThread = new Thread(customeView);
		shadingThread.start();
	}
}
