package com.example.exbandibletest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	TextView txt_help_gest  , txt_help_gest1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txt_help_gest = (TextView) findViewById(R.id.txt_help_gest);
		txt_help_gest1 = (TextView) findViewById(R.id.txt_help_gest1);
		// hide until its title is clicked
		txt_help_gest.setVisibility(View.GONE);
		txt_help_gest1.setVisibility(View.GONE);
	}

	public void toggle_contents(View v) {

		if (txt_help_gest.isShown()) {
			Fx.collapse(txt_help_gest);
			//txt_help_gest.setVisibility(View.GONE);
		} else {
			//txt_help_gest.setVisibility(View.VISIBLE);
			Fx.expand(txt_help_gest);
		}
	}
	
	public void toggle_contents1(View v) {

		if (txt_help_gest1.isShown()) {
			Fx.collapse(txt_help_gest1);
			//txt_help_gest1.setVisibility(View.GONE);
		} else {
			//txt_help_gest1.setVisibility(View.VISIBLE);
			Fx.expand(txt_help_gest1);
		}
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
}
