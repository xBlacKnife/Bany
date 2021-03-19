package com.example.awsdemo;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.awsdemo.api.control.ArtifactController;
import com.example.awsdemo.api.model.CloudfrontObserver;
import com.example.awsdemo.api.model.requests.Cloud;

import java.util.ArrayList;

public class CloudfrontActivity extends AppCompatActivity implements CloudfrontObserver {

    // View References
    // --------------------------
    private Spinner artifactSpinner;
    private TextView cloudfrontText;
    private ImageView cloudfrontImage;
    private LinearLayout audioContainer;
    private MediaPlayer cloudfrontAudio;
    private ArtifactController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloudfront_test);

        // Locate references
        this.artifactSpinner = findViewById(R.id.artifactSpinner);
        this.cloudfrontText = findViewById(R.id.artifactDescription);
        this.cloudfrontImage  = findViewById(R.id.artifactImage);
        this.audioContainer = findViewById(R.id.artifactAudioContainer);
        this.cloudfrontAudio = null;
        this.controller = ArtifactController.getInstance();

        // Initial View State
        ArrayList<String> items = controller.getRoArtifacts();
        ArrayAdapter<String> spinnerItems = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spinnerItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.artifactSpinner.setAdapter(spinnerItems);
        this.artifactSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();

                artifactSpinner.setEnabled(false);
                Cloud.getInstance().downloadArtifact(controller.getArtifact(selection));
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) { }
        });
        Cloud.getInstance().asCloudObserver().addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Cloud.getInstance().asCloudObserver().removeObserver(this);
    }

    // CloudfrontObserver
    // ------------------------------------------------------
    @Override
    public void onCloudfrontDescriptionDownload(String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontText.setText(text);
            }
        });
    }

    @Override
    public void onCloudfrontImageDownload(Bitmap image) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontImage.setImageBitmap(image);
            }
        });
    }

    @Override
    public void onCloudfrontAudioDownload(MediaPlayer mplayer) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontAudio = mplayer;
                cloudfrontAudio.start();
            }
        });
    }

    @Override
    public void onDownloadComplete() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                artifactSpinner.setEnabled(true);
            }
        });
    }

    @Override
    public void onDownloadFailed(String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                artifactSpinner.setEnabled(true);
            }
        });
    }
}
