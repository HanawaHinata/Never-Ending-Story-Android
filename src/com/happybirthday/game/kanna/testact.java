package com.happybirthday.game.kanna;

import java.io.IOException;  
import android.app.Activity;  
import android.media.AudioManager;  
import android.media.MediaPlayer;  
import android.os.Bundle;  
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
  
public class testact extends Activity{  
  
    private SurfaceView surface1;  
    private MediaPlayer mediaPlayer1;  
  
    private int postion = 0;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.test);  
        surface1 = (SurfaceView) findViewById(R.id.sv);  
 
        mediaPlayer1 = new MediaPlayer();  
        //设置播放时打开屏幕  
        surface1.getHolder().setKeepScreenOn(true);  
        surface1.getHolder().addCallback(new SurfaceViewLis());  
        
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
    }  
  
 
  
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