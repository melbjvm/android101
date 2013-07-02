package org.melbjvm.android.graphics;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CustomView extends View implements OnTouchListener, Runnable {

	private boolean stopped = true;
	private int x = 0;
	private int y = 550;
	private Drawable image_melbjvm;
	private Random random;
	private int screenWidth;
	private int screenHeight;
	private boolean initialised = false;
	private Drawable backgroundImage;

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
		image_melbjvm.setBounds(x, y, x + image_melbjvm.getIntrinsicWidth(), y
				+ image_melbjvm.getIntrinsicHeight());
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
				x = (x + (random.nextInt()) % 3) % screenWidth;
				y = (y + (random.nextInt()) % 3) % screenHeight;

				if (x < 0) {
					x = 0;
				}
				if (y < 0) {
					y = 0;
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
