//
// Created by zhongjihao on 19-08-26.
//


package com.android.ffmpegplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private FFVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.sample_text);
        mVideoView = findViewById(R.id.videoView);
    }

    public void onButtonClick(View view) {
        int id = view.getId();

        switch (id) {
            /*
            case R.id.button_protocol:
                setInfoText(Ffplayer.urlProtocolInfo());
                break;
            case R.id.button_codec:
                setInfoText(Ffplayer.avCodecInfo());
                break;
            case R.id.button_filter:
                setInfoText(Ffplayer.avFilterInfo());
                break;
            case R.id.button_format:
                setInfoText(Ffplayer.avFormatInfo());
                break;
                */
            case R.id.button_play:
                //String videoPath = Environment.getExternalStorageDirectory() + "/Movies/PERU.MP4";
                String videoPath = Environment.getExternalStorageDirectory() + "/DCIM/camera/test.mp4";

                mVideoView.playVideo(videoPath);
                break;
        }
    }

    private void setInfoText(String content) {
        if (mTextView != null) {
            mTextView.setText(content);
        }
    }

}
