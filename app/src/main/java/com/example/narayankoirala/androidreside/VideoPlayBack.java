package com.example.narayankoirala.androidreside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

/**
 * Created by Narayan Koirala on 1/3/2016.
 */
public class VideoPlayBack extends Activity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        Intent intent = getIntent();
        int position = intent.getIntExtra("id",0);
        videoView = (VideoView)findViewById(R.id.video_playback);
        videoView.setVideoPath("/sdcard/"+position+".mp4");
        videoView.start();
    }
}
