package es.ucm.bany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import es.ucm.bany.api.control.ArtifactController;
import es.ucm.bany.api.model.ApiObserver;
import es.ucm.bany.api.model.Artifact;
import es.ucm.bany.api.model.requests.Cloud;


import java.util.ArrayList;
import java.util.HashMap;

public class ApiActivity extends AppCompatActivity implements ApiObserver {

    // View References
    // --------------------------
    private TextView apiCountText;
    private TextView apiResponseText;
    private Button apiBtn;
    private Button nextBtn;
    private Spinner apiSpinner;
    private ArtifactController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.api_test);

        // Locate references
        this.apiCountText = findViewById(R.id.apiSizeText);
        this.apiResponseText = findViewById(R.id.apiText);
        this.apiBtn  = findViewById(R.id.apiButton);
        this.nextBtn = findViewById(R.id.nextButton);
        this.apiSpinner = findViewById(R.id.apiSpinner);
        this.controller = ArtifactController.getInstance();

        // Initial View State
        this.nextBtn.setEnabled(false);
        this.apiCountText.setText("Artifacts in app: " + this.controller.artifactCount());
        this.apiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();

                // Debug
                apiResponseText.setText(controller.getArtifact(selection).toString());
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) { }
        });
        Cloud.getInstance().asApiObserver().addObserver(this);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Cloud.getInstance().asApiObserver().removeObserver(this);
    }

    public void onApiButtonClick(View view) {
        apiBtn.setEnabled(false);
        Cloud.getInstance().doGetArtifacts();
    }

    public void onNextButtonClick(View view) {
        Intent nextView = new Intent(this, CloudfrontActivity.class);
        startActivity(nextView);
    }

    // ApiObserver
    // ------------------------------------------------------
    public void onApiHealthCheckSuccess() {}
    public void onApiHealthCheckError(String error) {}

    public void onApiGetArtifactsSuccess(HashMap<String, Artifact> artifacts) {
        ApiActivity self = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> items = controller.getRoArtifacts();
                ArrayAdapter<String> spinnerItems = new ArrayAdapter<String>(self, android.R.layout.simple_spinner_item, items);
                spinnerItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                apiSpinner.setAdapter(spinnerItems);

                apiResponseText.setText(controller.toString());
                apiCountText.setText("Artifacts in app: " + controller.artifactCount());

                nextBtn.setEnabled(true);
            }
        });
    }

    public void onApiGetArtifactsError(String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiCountText.setText("Artifacts in app: " + controller.artifactCount());
                nextBtn.setEnabled(false);
                apiBtn.setEnabled(true);
            }
        });
    }
}