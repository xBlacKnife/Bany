package com.example.awsdemo.api.model;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

public interface CloudfrontObserver {

    // Std Download
    void onCloudfrontDescriptionDownload(String text);
    void onCloudfrontImageDownload(Bitmap image);
    void onCloudfrontAudioStream(MediaPlayer player);
    void onDownloadComplete();
    void onDownloadFailed(String error);

}
