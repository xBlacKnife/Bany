package es.ucm.bany.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ucm.bany.MainActivity;
import es.ucm.bany.R;
import es.ucm.bany.artifacts.ArtifactViewItem;
import es.ucm.bany.aws.control.ArtifactController;
import es.ucm.bany.aws.model.CloudfrontObserver;
import es.ucm.bany.aws.model.requests.Cloud;

@SuppressLint("ValidFragment")
public class ArtifactInfo extends Fragment implements CloudfrontObserver {

    RelativeLayout buttonsLayout;
    Button preButton;
    Button nextButton;

    FloatingActionButton _playButton;

    private TextView cloudfrontText;
    public static MediaPlayer cloudAudioPlayer;
    private ImageView cloudfrontImage;

    ArtifactViewItem item;
    int itemIndex;
    private ArtifactController controller;

    private boolean free = false;

    public ArtifactInfo(int itemIndex, boolean free) {
        super();
        this.itemIndex = itemIndex;
        this.item = ArtifactViewItem.ITEMS[itemIndex];
        this.controller = ArtifactController.getInstance();
        this.free = free;

        Cloud.getInstance().asCloudObserver().addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Cloud.getInstance().asCloudObserver().removeObserver(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_artifact_info, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Cloud.getInstance().downloadArtifact(controller.getArtifact(item.getNameID()));
        _playButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_artifact_info_play);

        cloudfrontText = view.findViewById(R.id.textview_artifact_info_artifactdescription);
        cloudfrontImage = view.findViewById(R.id.imageview_artifact_info_artifactimage);
        cloudAudioPlayer = null;

        cloudfrontImage.setImageResource(item.getIdDrawable());
        cloudfrontText.setText(item.getNombre());

        buttonsLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_artifact_info_buttons);
        if(this.free) {
            buttonsLayout.setVisibility(View.INVISIBLE);
        }
        else{
            preButton = (Button) view.findViewById(R.id.button_artifact_info_pre);
            if(this.itemIndex == 0){
                preButton.setVisibility(View.INVISIBLE);
            }
            nextButton = (Button) view.findViewById(R.id.button_artifact_info_next);
            if(this.itemIndex == ArtifactViewItem.ITEMS.length - 1){
                nextButton.setVisibility(View.INVISIBLE);
            }
        }

        TextView t = view.findViewById(R.id.textview_artifact_info_artifacttitle);
        t.setText(item.getNombre());

        _playButton.setEnabled(false);
        initFragmentElements();
    }

    private void initFragmentElements() {

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if( i == KeyEvent.KEYCODE_BACK )
                {
                    if (cloudAudioPlayer != null) cloudAudioPlayer.stop();
                    return false;
                }
                return false;
            }
        });

        _playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cloudAudioPlayer == null) return;
                if (!cloudAudioPlayer.isPlaying()) {
                    _playButton.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), android.R.drawable.ic_media_pause));
                    cloudAudioPlayer.start();
                } else {
                    _playButton.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), android.R.drawable.ic_media_play));
                    cloudAudioPlayer.pause();
                }
            }
        });

        if(!this.free){
            preButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemIndex > 0) {
                        if (cloudAudioPlayer != null) cloudAudioPlayer.stop();
                        Fragment artifactView = new ArtifactInfo(itemIndex - 1, false);
                        FragmentManager fragmentManager = ((MainActivity) view.getContext()).getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();

                        transaction.replace(R.id.artifact_info, artifactView);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
            });

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemIndex < ArtifactViewItem.ITEMS.length - 1) {
                        if (cloudAudioPlayer != null) cloudAudioPlayer.stop();

                        Fragment artifactView = new ArtifactInfo(itemIndex + 1, false);
                        FragmentManager fragmentManager = ((MainActivity) view.getContext()).getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();

                        transaction.replace(R.id.artifact_info, artifactView);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
            });
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    cloudAudioPlayer.stop();
                    FragmentManager fragmentManager = ((MainActivity) v.getContext()).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    if(free){
                        Fragment freeview = new FreeView();
                        transaction.replace(R.id.free_view, freeview);
                    }
                    else{
                        Fragment guidedview = new GuidedView();
                        transaction.replace(R.id.guided_view, guidedview);
                    }
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;
                }
                return false;
            }
        } );

    }

    // CloudfrontObserver
    // ------------------------------------------------------
    @Override
    public void onCloudfrontDescriptionDownload(String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontText.setText(text);
            }
        });
    }

    @Override
    public void onCloudfrontImageDownload(Bitmap image) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cloudfrontImage.setImageBitmap(image);
            }
        });
    }

    @Override
    public void onCloudfrontAudioStream(MediaPlayer player) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                cloudAudioPlayer = player;
            }
        });
    }

    @Override
    public void onDownloadComplete() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _playButton.setEnabled(true);
            }
        });
    }

    @Override
    public void onDownloadFailed(String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(error);
                alertDialog.show();
            }
        });
    }

}