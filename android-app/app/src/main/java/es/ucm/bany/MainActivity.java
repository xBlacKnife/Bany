package es.ucm.bany;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import es.ucm.bany.aws.control.ArtifactController;
import es.ucm.bany.aws.model.requests.Cloud;
import es.ucm.bany.fragments.ArtifactInfo;

public class MainActivity extends AppCompatActivity {

    private static boolean requested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create Controller
        ArtifactController.getInstance();

        // Locate artifacts
        if (!requested) {
            requested = true;
            Cloud.getInstance().doGetArtifacts();
        }
        Utils.onActivityCreateSetTheme(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (ArtifactInfo.cloudAudioPlayer != null) {
            //ArtifactInfo.cloudAudioPlayer.stop();
            ArtifactInfo.cloudAudioPlayer.release();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}