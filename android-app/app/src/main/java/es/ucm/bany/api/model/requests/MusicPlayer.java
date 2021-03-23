package es.ucm.bany.api.model.requests;

import android.media.AudioManager;
import android.media.MediaPlayer;

import es.ucm.bany.api.model.Artifact;

public class MusicPlayer extends MediaPlayer {

    // Attributes
    // ------------------------------------------------------
    private String endpoint;

    // Constructors
    // ------------------------------------------------------
    public MusicPlayer(String url) {
        super();
        setAudioStreamType(AudioManager.STREAM_MUSIC);
        setVolume(100f, 100f);
        endpoint = url;
    }

    // Behaviour
    // ------------------------------------------------------
    protected void capture(Artifact a) {
        if (a.audioFile() != null) {
            new AudioStreaming(this).execute(endpoint);
        }
    }

}
