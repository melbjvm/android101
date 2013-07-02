package org.melbjvm.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * @author Zaki Anwar Hamdani
 * 
 *         An implementation of <code>android.app.Activity</code> and
 *         <code>android.widget.SeekBar.OnSeekBarChangeListener</code> that
 *         converts temperature value from Celsius to Fahrenheight and vice
 *         versa.
 * 
 *         Created exclusively for Melbourne JVM Meetup, July 2013
 * 
 */
public class MainActivity extends Activity implements OnSeekBarChangeListener {

	/**
	 * The text that displays results of conversion to the user.
	 */
	private TextView result;

	/**
	 * A text position to display some comments. Put your imagination there!!!
	 */
	private TextView comment;

	/**
	 * The seek bar element that is used to get input value from user.
	 */
	private SeekBar seekBar;

	/**
	 * The radio button group that contains radio button to choose between types
	 * of conversion.
	 */
	private RadioGroup conversioType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// try catch is not required. Its just for sake of checking exception
		try {

			seekBar = (SeekBar) findViewById(R.id.seekbar);
			result = (TextView) findViewById(R.id.result);
			comment = (TextView) findViewById(R.id.comment);
			conversioType = (RadioGroup) findViewById(R.id.conversion);
			seekBar.setOnSeekBarChangeListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

		String result = "";

		if (R.id.ctof == conversioType.getCheckedRadioButtonId()) {
			result = result
					+ seekBar.getProgress()
					+ " Celsius equals "
					+ String.valueOf(32 + ((9.0 / 5.0) * seekBar.getProgress()))
					+ " Fahrenheights";

		} else if (R.id.ftoc == conversioType.getCheckedRadioButtonId()) {
			result = result + seekBar.getProgress() + " Fahrenheight equals "
					+ String.valueOf((seekBar.getProgress() - 32) * 5.0 / 9.0)
					+ " Celsius";

		}

		this.result.setText(result);

		if ("".equals(result)) {
			this.comment.setText("Thats funny... What a Conversion !!!");

		} else {
			this.comment.setText("Voila...Thats Cool !!!");

		}

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// nothing to do

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// nothing to do

	}
}
