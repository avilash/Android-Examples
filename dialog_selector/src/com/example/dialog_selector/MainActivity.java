package com.example.dialog_selector;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

	Button btn_open_multi_dialog;
	Button btn_open_single_dialog;
	
	ArrayList mSelectedItems;
	int selected_item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_open_multi_dialog = (Button)findViewById(R.id.btn_open_multi_dialog);
		btn_open_single_dialog = (Button)findViewById(R.id.btn_open_single_dialog);
		btn_open_multi_dialog.setOnClickListener(this);
		btn_open_single_dialog.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_open_multi_dialog)
		{
			openMultiDialog();
		}
		if(v.getId() == R.id.btn_open_single_dialog)
		{
			openSingleDialog();
		}
	}
	
	
	public void openMultiDialog()
	{
		mSelectedItems = new ArrayList(); ;
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		CharSequence[] items = new CharSequence[] {"First", "Second", "Third"};
		boolean[] bools = new boolean[]{false, false , false};
		adb.setMultiChoiceItems(items, bools, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
            public void onClick(DialogInterface dialog, int which,boolean isChecked) {
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    mSelectedItems.add(which);
                } else if (mSelectedItems.contains(which)) {
                    // Else, if the item is already in the array, remove it 
                    mSelectedItems.remove(Integer.valueOf(which));
                }
            }
		});
		adb.setNegativeButton("Cancel" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	Toast.makeText(MainActivity.this, "You chose to Cancel" , Toast.LENGTH_SHORT).show();
            }
		});
		adb.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            
			@Override
            public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(MainActivity.this, mSelectedItems.toString() , Toast.LENGTH_SHORT).show();
            }
        });
		adb.setTitle("Multiple Slect");
		adb.show();
	}
	
	public void openSingleDialog()
	{
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final CharSequence[] items1 = new CharSequence[] {"First", "Second", "Third"};
		boolean[] bools = new boolean[]{false, false , false};
		adb.setSingleChoiceItems(items1, 1,  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				selected_item = which;
			}
		});
		adb.setNegativeButton("Cancel" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	Toast.makeText(MainActivity.this, "You chose to Cancel" , Toast.LENGTH_SHORT).show();
            }
		});
		adb.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            
			@Override
            public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(MainActivity.this, Integer.toString(selected_item) , Toast.LENGTH_SHORT).show();
            }
        });
		adb.setTitle("Single Slect");
		adb.show();
	}

}
