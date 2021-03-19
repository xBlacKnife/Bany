package com.example.awsdemo.api.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface ApiObserver {

    // Healthcheck handlers.
    void onApiHealthCheckSuccess();
    void onApiHealthCheckError(String error);

    // Get Artifact handlers.
    void onApiGetArtifactsSuccess(HashMap<String, Artifact> artifacts);
    void onApiGetArtifactsError(String error);

}
