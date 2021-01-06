//
// Created by zhongjihao on 19-08-26.
//


package com.android.ffmpegplayer;

import android.view.Surface;

public class Ffplayer {
    public static native String urlProtocolInfo();

    public static native String avFormatInfo();

    public static native String avCodecInfo();

    public static native String avFilterInfo();

    public static native void playVideo(String videoPath, Surface surface);

    static {
        System.loadLibrary("playjni");
    }
}
