package es.ucm.bany.aws.control;


import es.ucm.bany.aws.model.Artifact;
import es.ucm.bany.aws.model.ApiObserver;
import es.ucm.bany.aws.model.requests.Cloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ArtifactController implements ApiObserver {
    // Singleton
    // ------------------------------------------------------
    private final static  ArtifactController instance = new ArtifactController();

    // Singleton accesor.
    public static ArtifactController getInstance() { return instance; }

    // Attributes
    // ------------------------------------------------------
    private HashMap<String, Artifact> artifacts;

    // Constructors
    // ------------------------------------------------------
    private ArtifactController() {
        this.artifacts = new HashMap<>();
        Cloud.getInstance().asApiObserver().addObserver(this);
    }

    // Observers
    // ------------------------------------------------------
    public int artifactCount() {
        return this.artifacts.size();
    }

    public ArrayList<String> getRoArtifacts() {
        return new ArrayList<>(this.artifacts.keySet());
    }

    public Artifact getArtifact(String name) {
        return this.artifacts.get(name);
    }

    // ApiObserver
    // ------------------------------------------------------
    public void onApiHealthCheckSuccess() {}
    public void onApiHealthCheckError(String error) {
        this.artifacts.clear();
    }

    public void onApiGetArtifactsSuccess(HashMap<String, Artifact> response) {
        this.artifacts = (HashMap<String, Artifact>) response.clone();
    }

    public void onApiGetArtifactsError(String error) {
        this.artifacts.clear();
    }

    // Overrides (DEBUG)
    // ------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");

        Set<String> keys = this.artifacts.keySet();
        for(String key : keys) {
            sb.append(this.artifacts.get(key).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
