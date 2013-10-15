package com.example.soapapplication;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SOAPMain extends Activity {

	private final String NAMESPACE = "http://tempuri.org/";
	private final String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
	private final String SOAP_ACTION = "http://tempuri.org/CelsiusToFahrenheit";
	private final String METHOD_NAME = "CelsiusToFahrenheit";
	private static final String TAG = "Aplikacja SOAP";
	private static String celciusza;
	private static String fahrenheita;
	Button b;
	TextView tv;
	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soapmain);
	    et = (EditText) findViewById(R.id.editText1);
	    tv = (TextView) findViewById(R.id.tv_result);
	    b = (Button) findViewById(R.id.button1);
	    b.setOnClickListener(new OnClickListener() {
	    	@Override
			public void onClick(View arg0) {
	    		if(isOnline())
	    		{
		            if (et.getText().length() != 0 && et.getText().toString() != "") {
		                celciusza = et.getText().toString();
		                WebService task = new WebService();
		                task.execute();
		            } else {
		                tv.setText("Wprowadü ∞ Celciusza");
		            }
	    		}
	    		else
                {
                	Toast.makeText(getApplicationContext(), "Brak po≥πczenia z internetem", Toast.LENGTH_SHORT).show();
                }
				
			}
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.soapmain, menu);
		return true;
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	private class WebService extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            getFahrenheit(celciusza);
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            tv.setText(fahrenheita + "∞ F");
        }
 
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            tv.setText("Przeliczanie...");
        }
 
        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }
 
    }
	
	public void getFahrenheit(String c) {

	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    PropertyInfo celsiusPI = new PropertyInfo();
	    celsiusPI.setName("Celsius");
	    celsiusPI.setValue(c);
	    celsiusPI.setType(double.class);
	    request.addProperty(celsiusPI);
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
	            SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    envelope.setOutputSoapObject(request);
	    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 
	    try {
	        androidHttpTransport.call(SOAP_ACTION, envelope);
	        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
	        fahrenheita = response.toString();
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
