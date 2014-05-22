package com.example.wificheck;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	public JSONObject wifi_info = null;
	String ssid , ip;
	public TextView txt_connection_info , txt_ssid_info , txt_ip_info;
	
	private Handler handler;
    private Runnable runnable;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_connection_info = (TextView)findViewById(R.id.txt_connection_info);
        txt_ssid_info = (TextView)findViewById(R.id.txt_ssid_info);
        txt_ip_info = (TextView)findViewById(R.id.txt_ip_info);
        
        showWifiInfo();
        
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
            	txt_connection_info.setText("Checking..........");
            	Log.i("handler", "checking info");
            	showWifiInfo();
            	handler.postDelayed(this , 4000);
            	
            }
        };
        runnable.run();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	public void onStop()
	{
		super.onStop();
		handler.removeCallbacks(runnable);
	}
    
    public void showWifiInfo()
    {
        wifi_info = getCurrentSsid(this);
        if(wifi_info!=null)
        {
        	try {
				ssid = wifi_info.getString("ssid");
				ip = wifi_info.getString("ip");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
        if(ssid == null)
        {
        	txt_connection_info.setText("Not Connected to any Wifi");
        }
        else
        {
        	if(ssid.equalsIgnoreCase("LEAF WIFI"))
        	{
        		txt_connection_info.setText("Connected to Leaf WIFI");
        		txt_ssid_info.setText(ssid);
        		txt_ip_info.setText(ip);
        	}
        	else
        	{
        		txt_connection_info.setText("Not Connected to Leaf WIFI");
        		txt_ssid_info.setText(ssid);
        		txt_ip_info.setText(ip);
        	}
        }
    }
    
    public JSONObject getCurrentSsid(Context context) {
    	String ssid = null , ipString = null;
    	int ip = 0;
    	JSONObject retval = new JSONObject();
    	ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	  	NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	  	if (networkInfo.isConnected()) {
	  		final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    	    final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
    	    if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
    	    	ssid = connectionInfo.getSSID();
    	    }
    	    if (connectionInfo != null) {
    	    	ip = connectionInfo.getIpAddress();
    	    	ipString = String.format("%d.%d.%d.%d",(ip & 0xff),(ip >> 8 & 0xff),(ip >> 16 & 0xff),(ip >> 24 & 0xff));
    	    }
	  	}
	  	try {
			retval.put("ssid", ssid);
			retval.put("ip", ipString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
	  	return retval;
	}

    
}
