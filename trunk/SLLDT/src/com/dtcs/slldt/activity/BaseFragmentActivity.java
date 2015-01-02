package com.dtcs.slldt.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.Window;

import com.dtcs.slldt.common.DeviceInfoStore;
import com.edu.ebookcontact.R;

public class BaseFragmentActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		initScreenSize();
		setContentView(com.edu.ebookcontact.R.layout.activity_content);
	}
	
	/**
	 * Inits the screen size.
	 */
	@SuppressLint("NewApi")
	protected void initScreenSize(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			DeviceInfoStore.getInstance().setScreenWidth(size.x);
			DeviceInfoStore.getInstance().setScreenWidth(size.y);
		} else {
			Display display = getWindowManager().getDefaultDisplay();
			DeviceInfoStore.getInstance().setScreenWidth(display.getWidth());
			DeviceInfoStore.getInstance().setScreenWidth(display.getHeight());
		}
	}
	
	
	/**
     * add fragment
     * @param frag
     * @param isAddToBackStack
     */
    public void switchMenu(Fragment frag,boolean isAddToBackStack){
        FragmentTransaction fragManager = getSupportFragmentManager().beginTransaction();
        fragManager.replace(R.id.menu_frame, frag);
        if(isAddToBackStack){
            fragManager.addToBackStack(null);
        }
        fragManager.commit();
    }
    
    /**
     * add fragment with fragment name
     * @param frag
     * @param isAddToBackStack
     * @param name
     */
    public void switchMenu(Fragment frag,boolean isAddToBackStack,String name){
        FragmentTransaction fragManager = getSupportFragmentManager().beginTransaction();
        fragManager.replace(R.id.menu_frame, frag);
        if(isAddToBackStack){
            fragManager.addToBackStack(name);
        }
        fragManager.commit();
    }
    
	/**
     * add fragment
     * @param frag
     * @param isAddToBackStack
     */
    public void switchContent(Fragment frag,boolean isAddToBackStack){
        FragmentTransaction fragManager = getSupportFragmentManager().beginTransaction();
        fragManager.replace(R.id.content_frame, frag);
        if(isAddToBackStack){
            fragManager.addToBackStack(null);
        }
        fragManager.commit();
    }
    
    /**
     * add fragment with fragment name
     * @param frag
     * @param isAddToBackStack
     * @param name
     */
    public void switchContent(Fragment frag,boolean isAddToBackStack,String name){
        FragmentTransaction fragManager = getSupportFragmentManager().beginTransaction();
        fragManager.replace(R.id.content_frame, frag);
        if(isAddToBackStack){
            fragManager.addToBackStack(name);
        }
        fragManager.commit();
    }
    
    /**
     * add fragment
     * @param frag
     * @param isAddToBackStack
     */
    public void addContent(Fragment frag,boolean isAddToBackStack){
        FragmentTransaction fragManager = getSupportFragmentManager().beginTransaction();
        fragManager.add(R.id.content_frame, frag);
        if(isAddToBackStack){
            fragManager.addToBackStack(null);
        }
        fragManager.commit();
    }
    
    /**
     * add fragment with fragment name
     * @param frag
     * @param isAddToBackStack
     * @param name
     */
    public void addContent(Fragment frag,boolean isAddToBackStack,String name){
        FragmentTransaction fragManager = getSupportFragmentManager().beginTransaction();
        fragManager.add(R.id.content_frame, frag);
        if(isAddToBackStack){
            fragManager.addToBackStack(name);
        }
        fragManager.commit();
    }
    
    public void clearBackStack(){
    	getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    
    public Fragment getTopStack(){
    	List<Fragment> frags = getSupportFragmentManager().getFragments();
    	if (frags!=null && frags.size()>0) {
			return frags.get(frags.size()-1);
		}
    	return null;
    }
    
}
