package es.ucm.bany.api.model.requests;

import es.ucm.bany.api.model.Artifact;
import es.ucm.bany.api.model.response.GetArtifactsJsonParser;

import java.util.HashMap;

class GetArtifacts extends Request<String, Void, String> {

    // Constructors
    // ------------------------------------------------------
    protected GetArtifacts(String url) {
        super(url);
    }

    // Overrides
    // ------------------------------------------------------
    protected void onSuccess(String response) {
        GetArtifactsJsonParser parser = new GetArtifactsJsonParser(response);
        HashMap<String, Artifact> artifacts = parser.getResponse();

        Cloud.getInstance().notifyGetArtifactsOk(artifacts);
    }

    protected void onError(String error) {
        Cloud.getInstance().notifyGetArtifactsError(error);
    }
}
