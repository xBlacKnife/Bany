package es.ucm.bany.classes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.slider.Slider;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import es.ucm.bany.MainActivity;
import es.ucm.bany.R;
import es.ucm.bany.artifacts.ArtifactViewItem;
import es.ucm.bany.fragments.ArtifactInfo;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if(position == sliderItems.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return this.sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView imageView;

        public SliderViewHolder(@NonNull View view){
            super(view);
            this.imageView = view.findViewById(R.id.image_slide);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Fragment artifactView = new ArtifactInfo(0, false);
                    FragmentManager fragmentManager = ((MainActivity) view.getContext()).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    transaction.replace(R.id.guided_view, artifactView);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        void setImage(SliderItem sliderItem){
            this.imageView.setImageResource(sliderItem.getImage());
        }


    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
