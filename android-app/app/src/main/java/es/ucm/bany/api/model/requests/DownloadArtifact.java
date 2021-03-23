package es.ucm.bany.api.model.requests;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import es.ucm.bany.api.model.Artifact;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadArtifact extends Request<String, Void, String> {
    // Attributes
    // ------------------------------------------------------
    protected Activity view;
    protected Artifact artifact;

    // Constructors
    // ------------------------------------------------------
    protected DownloadArtifact(String url, Artifact artifact) {
        super(url);
        this.artifact = artifact;
    }

    // Handlers
    // ------------------------------------------------------
    @Override
    protected String doInBackground(String... views) {
        try {
            requestDescription();
            requestImage();
            requestAudio();

            onSuccess(null);
        } catch (IOException e) {
            Log.println(Log.ERROR, "Http exception.", e.getLocalizedMessage());
            onError(e.getLocalizedMessage());
        }
        return null;
    }

    private void requestDescription() throws IOException {
        String response = null;

        if (artifact.descriptionFile() != null) {
            String file = String.format("%s/%s/%s", endpoint, artifact.name(), artifact.descriptionFile());
            response = simpleStreamDownload(file);
        }
        Cloud.getInstance().notifyTextDownload(response);
    }

    private void requestImage() throws IOException {
        Bitmap bmp = null;
        if (artifact.imageFile() != null) {
            String file = String.format("%s/%s/%s", endpoint, artifact.name(), artifact.imageFile());
            InputStream stream = complexStreamDownload(file);

            bmp = BitmapFactory.decodeStream(stream);
        }
        Cloud.getInstance().notifyImageDownload(bmp);
    }

    private void requestAudio() throws IOException {
        String file = String.format("%s/%s/%s", endpoint, artifact.name(), artifact.audioFile());
        MusicPlayer player = new MusicPlayer(file);
        player.capture(artifact);

        Cloud.getInstance().notifyAudioStream(player);
    }

    // Single Stream Request
    // ------------------------------------------------------
    private String simpleStreamDownload(String resourceUrl) throws IOException {
        URL url = new URL(resourceUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        int responseCode = urlConnection.getResponseCode();
        String response = readStream(urlConnection.getInputStream());

        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Stream download failed for artifact: " + resourceUrl);
        }
        return response;
    }

    private InputStream complexStreamDownload(String resourceUrl) throws IOException {
        URL url = new URL(resourceUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        int responseCode = urlConnection.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Stream download failed for artifact: " + resourceUrl);
        }
        return urlConnection.getInputStream();
    }

    // Overrides
    // ------------------------------------------------------
    protected void onSuccess(String response) {
        Cloud.getInstance().notifyDownloadOk();
    }

    protected void onError(String error) {
        Cloud.getInstance().notifyDownloadError(error);
    }
}
