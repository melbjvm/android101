package org.melbjvm.android.coverterusingws;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Zaki Anwar Hamdani
 * 
 *         An implementation of <code>android.app.Activity</code> that converts
 *         temperature value from Celsius to Fahrenheight by calling a web
 *         service.
 * 
 *         Created exclusively for Melbourne JVM Meetup, July 2013
 * 
 */
public class MainActivity extends Activity {

	/**
	 * The text that displays results of conversion to the user.
	 */
	protected TextView resultView;

	/**
	 * The EditText where user is supposed to enter temperature in Celsius.
	 */
	protected EditText temperature;

	/**
	 * To hold converted value.
	 */
	protected String convertedValue = null;

	
	//Data required for Web Service Access
	private final String NAMESPACE = "http://tempuri.org/";
	private final String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
	private final String SOAP_ACTION = "http://tempuri.org/CelsiusToFahrenheit";
	private final String METHOD_NAME = "CelsiusToFahrenheit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// try catch is not required. Its just for sake of checking exception
		try {

			temperature = (EditText) findViewById(R.id.temp);
			resultView = (TextView) findViewById(R.id.result);
			Button convertButton = (Button) findViewById(R.id.convert);
			convertButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					AsyncCallWS asyncCallWS = new AsyncCallWS();
					asyncCallWS.execute();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Calls Web Service to get converted value
	 * 
	 * @param celsius temperature in Celsius
	 */
	public void getFahrenheit(String celsius) {

		// Create request
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		// Property which holds input parameters
		PropertyInfo celsiusPI = new PropertyInfo();
		// Set Name
		celsiusPI.setName("Celsius");
		// Set Value
		celsiusPI.setValue(celsius);
		// Set dataType
		celsiusPI.setType(double.class);
		// Add the property to request object
		request.addProperty(celsiusPI);
		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION, envelope);
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			// Assign it to fahren static variable
			convertedValue = response.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private class AsyncCallWS extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			getFahrenheit(temperature.getText().toString());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			resultView.setText(convertedValue);
		}

		@Override
		protected void onPreExecute() {
			resultView.setText("Calculating...");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

	}
}
