package com.happybirthday.game.kanna;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import android.os.*;
import android.content.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
//import com.badlogic.gdx.*;

public class splashact extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟五秒
	private AlphaAnimation mHideAnimation;

	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
		final ImageView imgview = (ImageView) findViewById(R.id.splash_bac);
		final TextView text =(TextView)findViewById(R.id.splash_text);
		
		final int num=loading();//载入
		//Gdx.input.vibrate(2000);
		 new Handler().postDelayed(new Runnable() {//用于延时
				public void run() {
					if(num==0){
						setHideAnimation(imgview,2000);//渐隐图片
						intent();//定时跳转
					}
					else{
						text.setText("参数错误，无法运行。");
					}
				}
			}, 5000);
		
		
		
    }
    /**
    *屏蔽返回键子函数，不被调用
    *
    */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        Toast.makeText(getApplicationContext(), "指令拒绝", Toast.LENGTH_SHORT).show();
    }
	/**
	*载入延时子函数
	*
	*/
	public int loading(){
		final TextView text =(TextView)findViewById(R.id.splash_text);
		// final int num;
		new Handler().postDelayed(new Runnable() {
				public void run() {
					text.setText("正在载入资源文件…");
				}
			}, 1000);
		new Handler().postDelayed(new Runnable() {
				public void run() {
					text.setText("正在检查存储设备状态…");
					
				}
			}, 2000);
		new Handler().postDelayed(new Runnable() {
				public void run() {
					text.setText("正在启动…");
					setHideAnimation(text,3000);

				}
			}, 5000);
		
			return 0;
		
	}
	/**
	*定时跳转子函数
	*
	*/
	public void intent(){
		new Handler().postDelayed(new Runnable() {
				public void run() {
					Intent mainIntent = new Intent(splashact.this,titleact.class);
					splashact.this.startActivity(mainIntent);
					splashact.this.finish();
				}
			}, SPLASH_DISPLAY_LENGHT);
	}
	
	/**
	 * View渐隐动画效果子函数
	 *
	 */
	private int setHideAnimation( View view, int duration ){
		if( null == view || duration < 0 ){
			return 3;
		}
		if( null != mHideAnimation ){
			mHideAnimation.cancel( );
		}
		mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
		mHideAnimation.setDuration( duration );
		mHideAnimation.setFillAfter( true );
		view.startAnimation( mHideAnimation );

		return 1;
	}
	
	/**
	 * 所有View渐隐动画效果
	 *
	 *
	private int allsetHideAnimation( View view,View view2,View view3 ,View view4,int duration ){
		if( null == view || duration < 0 && null == view2 || duration < 0&&null == view3 || duration < 0&&null == view4 || duration < 0){
			return 3;
		}
		if( null != mHideAnimation ){
			mHideAnimation.cancel( );
		}
		mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
		mHideAnimation.setDuration( duration );
		mHideAnimation.setFillAfter( true );
		view.startAnimation( mHideAnimation );
		view2.startAnimation( mHideAnimation );
		view3.startAnimation( mHideAnimation );
		view4.startAnimation( mHideAnimation );

		return 1;
	}
*/
}
