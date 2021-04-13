package es.ucm.bany.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ucm.bany.R;
import es.ucm.bany.artifacts.ArtifactViewItem;
import es.ucm.bany.aws.control.ArtifactController;
import es.ucm.bany.aws.model.CloudfrontObserver;
import es.ucm.bany.aws.model.requests.Cloud;

@SuppressLint("ValidFragment")
public class ArtifactInfo extends Fragment implements CloudfrontObserver {

    FloatingActionButton _playButton;
    private TextView cloudfrontText;
    public static MediaPlayer cloudAudioPlayer;
    private ImageView cloudfrontImage;

    ArtifactViewItem item;
    private ArtifactController controller;

    public ArtifactInfo(ArtifactViewItem item) {
        super();
        this.item = item;
        this.controller = ArtifactController.getInstance();

        Cloud.getInstance().asCloudObserver().addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Cloud.getInstance().asCloudObserver().removeObserver(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_artifact_info, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Cloud.getInstance().downloadArtifact(controller.getArtifact(item.getNombre()));
        _playButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_artifact_info_play);
        cloudfrontText = view.findViewById(R.id.textview_artifact_info_artifactdescription);
        cloudfrontImage = view.findViewById(R.id.imageview_artifact_info_artifactimage);
        cloudAudioPlayer = null;

        TextView t = view.findViewById(R.id.textview_artifact_info_artifacttitle);
        t.setText(item.getNombre());

        _playButton.setEnabled(false);
        initFragmentElements();
    }

    private void initFragmentElements() {
        _playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cloudAudioPlayer == null) return;
                if (!cloudAudioPlayer.isPlaying()) {
                    _playButton.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), android.R.drawable.ic_media_pause));
                    cloudAudioPlayer.start();
                } else {
                    _playButton.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), android.R.drawable.ic_media_play));
                    cloudAudioPlayer.pause();
                }
            }
        });

    }

    // CloudfrontObserver
    // ------------------------------------------------------
    @Override
    public void onCloudfrontDescriptionDownload(String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontText.setText(text);
            }
        });
    }

    @Override
    public void onCloudfrontImageDownload(Bitmap image) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontImage.setImageBitmap(image);
            }
        });
    }

    @Override
    public void onCloudfrontAudioStream(MediaPlayer player) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudAudioPlayer = player;
            }
        });
    }

    @Override
    public void onDownloadComplete() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _playButton.setEnabled(true);

            }
        });
    }

    @Override
    public void onDownloadFailed(String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(error);
                alertDialog.show();
            }
        });
    }

}