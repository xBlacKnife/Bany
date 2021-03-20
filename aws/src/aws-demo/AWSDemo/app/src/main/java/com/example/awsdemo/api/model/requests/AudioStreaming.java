package com.example.awsdemo.api.model.requests;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.util.Log;

public class AudioStreaming extends AsyncTask<String, Void, Boolean> {

    // Attributes
    // ------------------------------------------------------
    private MusicPlayer musicPlayer;

    // Constructors
    // ------------------------------------------------------
    public AudioStreaming(MusicPlayer player) {
        this.musicPlayer = player;
    }

    // Handlers
    // ------------------------------------------------------
    @Override
    protected Boolean doInBackground(String... params) {
        Boolean prepared = false;
        try {
            musicPlayer.setDataSource(params[0]);
            musicPlayer.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    musicPlayer.stop();
                    musicPlayer.reset();
                }
            });

            musicPlayer.prepare();
            prepared = true;
        } catch (IllegalArgumentException e) {
            Log.d("IllegarArgument", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prepared;
    }

}
