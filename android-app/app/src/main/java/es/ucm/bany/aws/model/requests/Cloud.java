package es.ucm.bany.aws.model.requests;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

import es.ucm.bany.aws.model.ApiObserver;
import es.ucm.bany.aws.model.Artifact;
import es.ucm.bany.aws.model.CloudfrontObserver;
import es.ucm.bany.aws.model.Observable;

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
    private final static String AWS_API = "https://fhbuou21ji.execute-api.eu-west-1.amazonaws.com/pro";
    private final static String AWS_CLOUDFRONT = "https://d2zuas8e2dv0g1.cloudfront.net";
    private final static String AWS_HEALTHCHECK = AWS_API;
    private final static String AWS_ARTIFACTS = AWS_API + "/artifacts";

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

        new DownloadArtifact(AWS_CLOUDFRONT, a).execute();
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

    protected void notifyAudioStream(MediaPlayer player) {
        clearCloudfrontNullPointers();
        for (CloudfrontObserver o : cloudfrontObservers) {
            o.onCloudfrontAudioStream(player);
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
