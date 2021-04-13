package es.ucm.bany.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.ucm.bany.R;
import es.ucm.bany.artifacts.ArtifactAdapter;

public class FreeView extends Fragment {

    ArtifactAdapter _artifactAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_free_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView gridView = (GridView) view.findViewById(R.id.grid);
        ArtifactAdapter artifactAdapter = new ArtifactAdapter(getContext());
        gridView.setAdapter(artifactAdapter);
    }
}

