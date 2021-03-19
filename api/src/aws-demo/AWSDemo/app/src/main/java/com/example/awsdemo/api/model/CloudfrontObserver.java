package com.example.awsdemo.api.model;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio;

public interface CloudfrontObserver {

    void onCloudfrontDescriptionDownload(String text);
    void onCloudfrontImageDownload(Bitmap image);
    void onCloudfrontAudioDownload(MediaPlayer mplayer);
    void onDownloadComplete();
    void onDownloadFailed(String error);
}
