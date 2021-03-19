package com.example.awsdemo.api.model.requests;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

import com.example.awsdemo.api.model.ApiObserver;
import com.example.awsdemo.api.model.Artifact;
import com.example.awsdemo.api.model.CloudfrontObserver;
import com.example.awsdemo.api.model.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Cloud {
    // Singleton
    // ------------------------------------------------------
    private final static Cloud instance = new Cloud();

    // Singleton accesor.
    public static Cloud getInstance() { return instance; }

    // Attributes
    // ------------------------------------------------------
    private final static String API = "";
    protected final static String AWS_HEALTHCHECK = API;
    protected final static String AWS_ARTIFACTS = API + "/artifacts";

    // Cloud observers
    private ArrayList<ApiObserver> apiObservers;
    private ArrayList<CloudfrontObserver> cloudfrontObservers;

    // Constructors
    // ------------------------------------------------------
    private Cloud() {
        apiObservers = new ArrayList<>();
        cloudfrontObservers = new ArrayList<>();
    }

    // Behaviour
    // ------------------------------------------------------
    public void doHealthcheck() {
        new Healthcheck(AWS_HEALTHCHECK).execute();
    }

    public void doGetArtifacts() {
        new GetArtifacts(AWS_ARTIFACTS).execute();
    }

    public void downloadArtifact(Artifact a) {
        if (a == null) return;

        if (a.descriptionFile() != null) {
            // TODO request text
        }
        if (a.imageFile() != null) {
            // TODO request image
        }
        if (a.audioFile() != null) {
            // TODO request audio
        }
    }

    // API Notifications
    // ------------------------------------------------------
    private void clearApiNullPointers() {
        apiObservers.removeAll(Collections.singleton(null));
    }

    protected void notifyHealthCheckOk() {
        clearApiNullPointers();
        for (ApiObserver o : apiObservers) {
            o.onApiHealthCheckSuccess();
        }
    }

    protected void notifyHealthCheckError(String error) {
        clearApiNullPointers();
        for (ApiObserver o : apiObservers) {
            o.onApiHealthCheckError(error);
        }
    }

    protected void notifyGetArtifactsOk(HashMap<String, Artifact> response) {
        clearApiNullPointers();
        for (ApiObserver o : apiObservers) {
            o.onApiGetArtifactsSuccess(response);
        }
    }

    protected void notifyGetArtifactsError(String error) {
        clearApiNullPointers();
        for (ApiObserver o : apiObservers) {
            o.onApiGetArtifactsError(error);
        }
    }

    // Cloudfront Notifications
    // ------------------------------------------------------
    private void clearCloudfrontNullPointers() {
        cloudfrontObservers.removeAll(Collections.singleton(null));
    }

    protected void notifyTextDownload(String text) {
        clearCloudfrontNullPointers();
        for (CloudfrontObserver o : cloudfrontObservers) {
            o.onCloudfrontDescriptionDownload(text);
        }
    }

    protected void notifyImageDownload(Bitmap image) {
        clearCloudfrontNullPointers();
        for (CloudfrontObserver o : cloudfrontObservers) {
            o.onCloudfrontImageDownload(image);
        }
    }

    protected void notifyAudioDownload(MediaPlayer mplayer) {
        clearCloudfrontNullPointers();
        for (CloudfrontObserver o : cloudfrontObservers) {
            o.onDownloadComplete();
        }
    }

    protected void notifyDownloadOk() {
        clearCloudfrontNullPointers();
        for (CloudfrontObserver o : cloudfrontObservers) {
            o.onDownloadComplete();
        }
    }

    protected void notifyDownloadError(String error) {
        clearCloudfrontNullPointers();
        for (CloudfrontObserver o : cloudfrontObservers) {
            o.onDownloadFailed(error);
        }
    }

    // Overrides
    // ------------------------------------------------------
    public Observable<ApiObserver> asApiObserver() {
        return new Observable<ApiObserver>() {

            @Override
            public void addObserver(ApiObserver o) {
                apiObservers.add(o);
            }

            @Override
            public void removeObserver(ApiObserver o) {
                apiObservers.remove(o);
            }
        };
    }

    public Observable<CloudfrontObserver> asCloudObserver() {
        return new Observable<CloudfrontObserver>() {

            @Override
            public void addObserver(CloudfrontObserver o) {
                cloudfrontObservers.add(o);
            }

            @Override
            public void removeObserver(CloudfrontObserver o) {
                cloudfrontObservers.remove(o);
            }
        };
    }

}
