package com.happybirthday.game.kanna;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.SurfaceView;
import android.os.*;
import android.view.*;
import java.io.IOException;
import android.view.SurfaceHolder;



public class MainVideoViewActivity extends Activity {
	private SurfaceView surface1;  
    private MediaPlayer mediaPlayer1;  
    private int postion = 0;  
	PowerManager powerManager = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.main_video_view);
		/**
		 * 以下三行用于设置当前activity亮度。
		 * 其中，screenBrightness = 1.0f;是主要的变量设置语句
		 * 它的值在0～1之间，但千万不要设置为0，否则屏幕会全黑而失去响应。
		 */
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.screenBrightness = 1.0f;
		getWindow().setAttributes(lp);
		
		//声明播放器
		surface1 = (SurfaceView) findViewById(R.id.sv);
		//声明mediaPlayer
		mediaPlayer1 = new MediaPlayer();  
		
		//设置播放时打开屏幕  
        surface1.getHolder().setKeepScreenOn(true);  
        surface1.getHolder().addCallback(new SurfaceViewLis());  
        //播放他妈的视频
        try {
			play();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		/**
		 * 如果surface1被点击，那么当前activity销毁，使用intent跳转回titleact
		 */
		surface1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final Intent intent = new Intent(MainVideoViewActivity.this,titleact.class);
				startActivity(intent);
				finish();
			}
		});
	
	}
	

/** 
 * 开始播放 
 * 
 * @param msec 播放初始位置 
 */ 

	public void play() throws IllegalArgumentException, SecurityException,  
    IllegalStateException, IOException {  
mediaPlayer1.reset();  
mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);  
mediaPlayer1.setDataSource("/mnt/sdcard/数据包/pv.mp4");  
// 把视频输出到SurfaceView上  
mediaPlayer1.setDisplay(surface1.getHolder());  
mediaPlayer1.prepare();  
mediaPlayer1.start();  
}  

private class SurfaceViewLis implements SurfaceHolder.Callback {  

@Override  
public void surfaceChanged(SurfaceHolder holder, int format, int width,  
        int height) {  

}  

@Override  
public void surfaceCreated(SurfaceHolder holder) {  
    if (postion == 0) {  
        try {  
            play();  
            mediaPlayer1.seekTo(postion);  
        } catch (IllegalArgumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalStateException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  

    }  

}  

@Override  
public void surfaceDestroyed(SurfaceHolder holder) {  

}  

}  

@Override  
protected void onPause() {  
if (mediaPlayer1.isPlaying()) {  
    // 保存当前播放的位置  
    postion = mediaPlayer1.getCurrentPosition();  
    mediaPlayer1.stop();  
}  
super.onPause();  
}  

@Override  
protected void onDestroy() {  
if (mediaPlayer1.isPlaying())  
    mediaPlayer1.stop();  
mediaPlayer1.release();  
super.onDestroy();  
}  



}
