package com.example.awsdemo.api.model.response;

import com.example.awsdemo.api.model.Artifact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GetArtifactsJsonParser {

    // Attributes
    // ------------------------------------------------------
    private HashMap<String, Artifact> artifacts;

    // Constructors
    // ------------------------------------------------------
    public GetArtifactsJsonParser(String json) {
        artifacts = new HashMap<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray arr = response.getJSONArray("artifacts");

            for(int i = 0; i < arr.length(); i++) {
                String name = arr.getJSONObject(i).getString("name");
                String desc = null;
                if (arr.getJSONObject(i).has("description")) {
                    desc = arr.getJSONObject(i).getString("description");
                }
                String image = null;
                if (arr.getJSONObject(i).has("image")) {
                    image = arr.getJSONObject(i).getString("image");
                }
                String audio = null;
                if (arr.getJSONObject(i).has("audio")) {
                    audio = arr.getJSONObject(i).getString("audio");
                }

                artifacts.put(name, new Artifact(name, desc, image, audio));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Artifact> getResponse() {
        return this.artifacts;
    }

}
