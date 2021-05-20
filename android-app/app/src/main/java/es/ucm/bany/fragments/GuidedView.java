package es.ucm.bany.fragments;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ucm.bany.MainActivity;
import es.ucm.bany.R;
import es.ucm.bany.artifacts.ArtifactViewItem;
import es.ucm.bany.classes.SliderAdapter;
import es.ucm.bany.classes.SliderItem;

@SuppressLint("ValidFragment")
public class GuidedView extends Fragment {

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guided_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2 = view.findViewById(R.id.viewpager_carrusel_guided_view);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.ajedrez));
        sliderItems.add(new SliderItem(R.drawable.amstrad));
        sliderItems.add(new SliderItem(R.drawable.atari));
        sliderItems.add(new SliderItem(R.drawable.discosduros));
        sliderItems.add(new SliderItem(R.drawable.macintoshse));
        sliderItems.add(new SliderItem(R.drawable.obleas));
        sliderItems.add(new SliderItem(R.drawable.paneljuegos));
        sliderItems.add(new SliderItem(R.drawable.panelrevistas));
        sliderItems.add(new SliderItem(R.drawable.segamega));
        sliderItems.add(new SliderItem(R.drawable.analizadordiff));
        sliderItems.add(new SliderItem(R.drawable.refrigeracion));
        sliderItems.add(new SliderItem(R.drawable.recreativa));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                android.app.Fragment artifactView = new ArtifactInfo(getAllItems(), position);
                FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.free_view, artifactView);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });*/

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
}