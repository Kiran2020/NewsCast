package com.example.narayankoirala.androidreside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.VideoView;

/**
 * Created by Pci on 1/2/2016.
 */
public class AddNewsActvity extends Activity {
    private Spinner dropdown;
    private VideoView videoView;
    String filePath;
    private static final String[] items = new String[]{"Political", "Socail issue", "BlackMail", "Caste Discrimination"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        dropdown = (Spinner) findViewById(R.id.dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        videoView = (VideoView) findViewById(R.id.videoPreview);
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");
        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoPath(filePath);
        // start playing
        videoView.start();
    }
}
