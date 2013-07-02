package org.melbjvm.android.graphics;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author Zaki Anwar Hamdani
 * 
 *         The custom view that keeps displaying an image continuously at random
 *         positions over another background image. The image motion can be
 *         stopped or started by tapping the device.
 * 
 */
public class CustomView extends View implements OnTouchListener, Runnable {

	private boolean stopped = true;
	private Drawable image_melbjvm;
	private Drawable backgroundImage;

	private Random random;

	private int currentPosition__X = 0;
	private int currentPosition__Y = 550;
	private int screenWidth;
	private int screenHeight;

	private boolean initialised = false;

	public CustomView(Context context) {
		super(context);

		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		image_melbjvm = context.getResources().getDrawable(R.drawable.melbjvm);
		backgroundImage = context.getResources().getDrawable(
				R.drawable.background);
		random = new Random();
		screenHeight = 800;
		screenWidth = 480;
	}

	@Override
	public void onDraw(Canvas canvas) {

		if (!initialised) {
			screenHeight = canvas.getHeight();
			screenWidth = canvas.getWidth();
			backgroundImage.setBounds(0, 0, screenWidth, screenHeight);
			initialised = true;
		}

		backgroundImage.draw(canvas);
		image_melbjvm.setBounds(currentPosition__X, currentPosition__Y,
				currentPosition__X + image_melbjvm.getIntrinsicWidth(),
				currentPosition__Y + image_melbjvm.getIntrinsicHeight());
		image_melbjvm.draw(canvas);

	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			stopped = !stopped;

		}
		return true;
	}

	@Override
	public void run() {
		while (true) {
			if (!stopped) {
				currentPosition__X = (currentPosition__X + (random.nextInt()) % 3)
						% screenWidth;
				currentPosition__Y = (currentPosition__Y + (random.nextInt()) % 3)
						% screenHeight;

				if (currentPosition__X < 0) {
					currentPosition__X = 0;
				}
				if (currentPosition__Y < 0) {
					currentPosition__Y = 0;
				}
				postInvalidate();

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
