package com.example.caraousel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public RelativeLayout rel_layout;
    private GestureDetector gestureDetector;
    OnTouchListener gestureListener;
    
    private LinearLayout mDotsLayout;
    static TextView mDotsText[];
    public int mDotsCount;
    Button button_loginScreen_login, button_loginScreen_create;
    int position = 0;
    int image_res_ids[];
    
    public Animation animationFadeIn;
    public Animation animationFadeOut;
    AnimationListener animListener;
    
    ImageView image1 ,image2;
    Typeface font, fontLeaf;
    
    private Handler handler;
    private Runnable runnable;
    
    boolean didUserChange = true;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		rel_layout = (RelativeLayout)findViewById(R.id.container);
		
		animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		image1 = (ImageView)findViewById(R.id.image_view);
		image2 = (ImageView)findViewById(R.id.image_view2);
		button_loginScreen_login = (Button)findViewById(R.id.btn_loginScreen_login);
		button_loginScreen_create = (Button)findViewById(R.id.btn_loginScreen_create);
        image_res_ids = new int[]{R.drawable.im_1 , R.drawable.im_2 , R.drawable.im_3 , R.drawable.im_4};
        
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
            	handler.postDelayed(this , 4000);
            	if(!didUserChange)
            	{
            		showNext();
            	}
            	didUserChange = false;
            }
        };

        //Deining indicators
        mDotsLayout = (LinearLayout)findViewById(R.id.image_count);
        mDotsCount = 4;
        mDotsText = new TextView[mDotsCount];
        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(this);
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(24);
            mDotsText[i].setTypeface(font, Typeface.BOLD);
            mDotsText[i].setTextColor(Color.BLACK);
            mDotsLayout.addView(mDotsText[i]);
        }
        
        mDotsText[0].setTextColor(Color.WHITE);
        
        //On touch Listener
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        rel_layout.setOnTouchListener(gestureListener);
        runnable.run();
        button_loginScreen_login.setOnClickListener(this);
        button_loginScreen_create.setOnClickListener(this);
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
	
	public void showPrevious()
	{
		if(position==0)
		{
			position = mDotsCount-1;
		}
		else
		{
			position--;
		}
		for (int i = 0; i < mDotsCount; i++) {
			mDotsText[i].setTextColor(Color.BLACK);
        }
        mDotsText[position].setTextColor(Color.WHITE);
        
        
        if(image1.getVisibility() == View.VISIBLE)
        {
        	image1.startAnimation(animationFadeOut);
        	image2.setImageResource(image_res_ids[position]);
        	image2.setVisibility(View.VISIBLE);
    	    image2.startAnimation(animationFadeIn);
    	    image1.setVisibility(View.INVISIBLE);
        }
        else
        {
        	image2.startAnimation(animationFadeOut);
        	image1.setImageResource(image_res_ids[position]);
        	image1.setVisibility(View.VISIBLE);
    	    image1.startAnimation(animationFadeIn);
    	    image2.setVisibility(View.INVISIBLE);
        }
        
		
	}

	public void showNext()
	{
		if(position==(mDotsCount-1))
		{
			position = 0;
		}
		else
		{
			position++;
		}
		for (int i = 0; i < mDotsCount; i++) {
			mDotsText[i].setTextColor(Color.BLACK);
        }
        mDotsText[position].setTextColor(Color.WHITE);
        
        if(image1.getVisibility() == View.VISIBLE)
        {
        	image1.startAnimation(animationFadeOut);
        	image2.setImageResource(image_res_ids[position]);
        	image2.setVisibility(View.VISIBLE);
    	    image2.startAnimation(animationFadeIn);
    	    image1.setVisibility(View.INVISIBLE);
        }
        else
        {
        	image2.startAnimation(animationFadeOut);
        	image1.setImageResource(image_res_ids[position]);
        	image1.setVisibility(View.VISIBLE);
    	    image1.startAnimation(animationFadeIn);
    	    image2.setVisibility(View.INVISIBLE);
        }
	}
	
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	didUserChange = true;
                	showNext();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	didUserChange = true;
                	showPrevious();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
              return true;
        }
    }
	@SuppressLint("InlinedApi")
	public void showLoginDialog() {
	    //((MainActivity)this).showLoginView();  
	}
	
	@SuppressLint("InlinedApi")
	public void showCreateDialog() {
	    //((MainActivity)this).showCreateView();  
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_loginScreen_login)
		{
			showLoginDialog();
		}
		else if(v.getId() == R.id.btn_loginScreen_create)
		{
			showCreateDialog();
		}
	}
	
	

}
