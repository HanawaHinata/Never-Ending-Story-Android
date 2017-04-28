package com.happybirthday.game.kanna;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.*;
import android.media.*;
import android.content.res.*;
import java.io.*;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import android.widget.*;
import android.view.animation.*;
import android.view.*;
import android.app.*;
import android.os.*;

public class titleact extends Activity
{

	private AlphaAnimation mHideAnimation;
	private AlphaAnimation mShowAnimation;
	final MediaPlayer mMediaPlayer = new MediaPlayer();
	 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//以下一行用于屏幕常亮
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.title);
		
		//声明可用元素
		final Button open = (Button)findViewById(R.id.to_gdx_btn);//剧情模式
		final Button exit = (Button)findViewById(R.id.exit_btn);//退出游戏
		final Button online = (Button)findViewById(R.id.to_online_btn);//线上模式
		final Button config = (Button)findViewById(R.id.config_btn);//环境设定
		final Button club = (Button)findViewById(R.id.nes_club_btn);//NES Club
		final ImageView imgview=(ImageView)findViewById(R.id.imgview);//背景图片
		final LinearLayout white_layout = (LinearLayout)findViewById(R.id.white_background);//右半部份的白色半透明背景
		final RelativeLayout hover = (RelativeLayout)findViewById(R.id.hover);//右半部份顶部的NES标志
		final TextView copyright = (TextView) findViewById(R.id.copyright);//右半部份下部的版本与版权信息

		/**
		*
		*以下代码用于控制背景音乐播放，改天把他们放到service里。
		*如果不放在service里，跳转到设置窗口时整个音乐可能会停掉。
		*
		*/

		AssetManager am = getAssets();
	
		try {
			AssetFileDescriptor afd = am.openFd("0.mp3");
			mMediaPlayer.reset();
//			mMediaPlayer.setDataSource("sdcard/数据包/album_other.bnxwav");

            mMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());  
  
            mMediaPlayer.prepare();  
            mMediaPlayer.start();  
            
            
            //声音渐显
            //延时函数
//            Timer timer = new Timer();
//            Task SetValuesUp = new Task(){
//            	float value_i = 0;
//                @Override
//                public void run() {
//                	mMediaPlayer.setVolume(value_i, value_i);
//                	value_i = (float) (value_i + 0.1);
//                }
//            };
//            timer.scheduleTask(SetValuesUp, 0,1,10);// 0s之后执行，每次间隔1s，执行10次。
            
			mMediaPlayer.setLooping(true);//设置音乐的循环播放
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//先把所有元素渐渐显示，alpha在2000ms时间里从0转到100。
		allsetShowAnimation(imgview,exit,club,config,online,open,white_layout,hover,copyright,1000);
		//设置 进入剧情模式 的按钮监听
		open.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					ImageView imgview=(ImageView)findViewById(R.id.imgview);
					allsetHideAnimation(imgview,open,exit,club,config,online,white_layout,hover,copyright,1000);
					final Intent intent = new Intent(titleact.this,MainActivity.class);
					new Handler().postDelayed(new Runnable() {
							public void run() {
								startActivity(intent);
								mMediaPlayer.pause();
								titleact.this.finish();
							}
						}, 1000);
				}
			});
			
		//设置 退出游戏 按钮的监听
		exit.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					long exitTime = 0;
					if ((System.currentTimeMillis() - exitTime) > 2000) {    
						Exit_dialog();
					}
					else {      
						finish();            
						System.exit(0);      
					}   
					
				} 
			});
			
		//设置 线上模式 按钮的监听
		online.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					ImageView imgview=(ImageView)findViewById(R.id.imgview);
					allsetHideAnimation(imgview,open,exit,online,club,config,hover,copyright,white_layout,1000);
					final Intent intent = new Intent(titleact.this,load_game_act.class);
					new Handler().postDelayed(new Runnable() {
							public void run() {
								startActivity(intent);
								mMediaPlayer.pause();
								titleact.this.finish();
							}
						}, 1000);
				}
			});
			
			
			
		//设置 环境设置 按钮的监听
		config.setOnClickListener(new OnClickListener(){
				//private Intent intent;
				@Override
				public void onClick(View arg0){
					Intent intent = new Intent();
					intent.setClass(titleact.this,config_act.class);
					startActivity(intent);
				}
			});
			
			

	}
	
	/**
	 * 所有View渐隐动画效果
	 *
	 */
	private int allsetHideAnimation( View view,View view2,View view3 ,View view4,View view5,View view6,View view7,View view8,View view9,int duration ){
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
		view5.startAnimation( mHideAnimation );
		view6.startAnimation( mHideAnimation );
		view7.startAnimation( mHideAnimation );
		view8.startAnimation( mHideAnimation );
		view9.startAnimation( mHideAnimation );
		
		return 0;
	}

	/**
	 * 所有View渐现动画效果
	 *
	 */
	private void allsetShowAnimation( View view,View view2,View view3,View view4, View view5,View view6,View view7,View view8,View view9,int duration ){
		if( null == view || duration < 0 ){
			return;
		}
		if( null != mShowAnimation ){
			mShowAnimation.cancel( );
		}
		mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
		mShowAnimation.setDuration( duration );
		mShowAnimation.setFillAfter( true );
		view.startAnimation( mShowAnimation );
		view2.startAnimation( mShowAnimation );
		view3.startAnimation( mShowAnimation );
		view4.startAnimation( mShowAnimation );
		view5.startAnimation( mShowAnimation );
		view6.startAnimation( mShowAnimation );
		view7.startAnimation( mShowAnimation );
		view8.startAnimation( mShowAnimation );
		view9.startAnimation( mShowAnimation );
	} 


	//对返回按钮做监听，覆盖掉原有的返回功能。这样按一下返回键会弹出退出提示。
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            Exit_dialog();
        }  
        return false;  

    }  
	
	
//	//设定一个计时器，60s后跳转到VideoView界面。
//	public boolean setTimeYanshi(int time){
//		new Handler().postDelayed(new Runnable() {
//			public void run() {
//				//
//				allsetShowAnimation(imgview,exit,club,config,online,open,white_layout,hover,copyright,1000);
//				MediaPlayer.pause();
//				final Intent intent = new Intent(titleact.this,MainVideoViewActivity.class);
//				startActivity(intent);
//				titleact.this.finish();
//			}
//		}, time);
//		return true;
//	}
	
	/**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
				case AlertDialog.BUTTON_POSITIVE:// "确认"按钮,退出程序  
					finish();  
					System.exit(0);
					break;  
				case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮,取消对话框  
					break;  
				default:  
					break;  
            }  
        }  
    };    
	
    
    //创建退出确认对话框
	@SuppressWarnings("deprecation")
	private void  Exit_dialog(){
		// 创建退出对话框  
		AlertDialog isExit = new AlertDialog.Builder(this).create();  
		// 设置对话框标题  
		isExit.setTitle("退出");  
		// 设置对话框消息  
		isExit.setMessage("您确定要退出 Never Ending Story 并返回主屏幕吗？");  
		// 添加选择按钮并注册监听  
		isExit.setButton("确认", listener);  
		isExit.setButton2("取消", listener);  
		// 显示对话框  
		isExit.show();  
		//return null;
	}

	
	

}
