package es.ucm.bany.fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.os.Handler;
import android.os.health.SystemHealthManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import es.ucm.bany.R;
import es.ucm.bany.Utils;


public class ArtifactInfo extends Fragment {

    FloatingActionButton _playButton;
    ProgressBar _progressBar;

    MediaPlayer _mediaPlayer;
    Boolean _isPlaying = false;

    Timer _timer;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artifact_info, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _playButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_artifact_info_play);
        _progressBar = (ProgressBar) view.findViewById(R.id.progressBar_artifact_info_soundprogress);

        _mediaPlayer = MediaPlayer.create(getContext(), R.raw.yoshi_song);

        createProgressBar();
        initFragmentElements();
    }


    /**
     * Le asigna el comportamiento a los elementos que hay en el Fragment
     */
    private void initFragmentElements() {

        _playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_isPlaying) {
                    _playButton.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), android.R.drawable.ic_media_pause));
                    _mediaPlayer.start();
                } else {
                    _playButton.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), android.R.drawable.ic_media_play));
                    _mediaPlayer.pause();
                }
                _isPlaying = !_isPlaying;
            }
        });

    } // initFragmentElements

    private void createProgressBar(){

        _timer = new Timer();
        _progressBar.setMax(_mediaPlayer.getDuration());

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (_mediaPlayer.isPlaying())
                    _progressBar.setProgress(_mediaPlayer.getCurrentPosition(), true);
            }
        };

        _timer.schedule(timerTask, 0, 100);

    } // createProgressBar
}