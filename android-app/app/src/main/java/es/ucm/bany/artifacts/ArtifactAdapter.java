package es.ucm.bany.artifacts;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.ucm.bany.MainActivity;
import es.ucm.bany.R;
import es.ucm.bany.classes.SliderItem;
import es.ucm.bany.fragments.ArtifactInfo;

public class ArtifactAdapter extends BaseAdapter {
    private Context context;

    private ImageView artifactImage;
    private TextView artifactText;


    public ArtifactAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ArtifactViewItem.ITEMS.length;
    }

    @Override
    public ArtifactViewItem getItem(int position) {
        return ArtifactViewItem.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);

            artifactImage = (ImageView) view.findViewById(R.id.imagen_coche);
            artifactText = (TextView) view.findViewById(R.id.nombre_coche);
        }

        final ArtifactViewItem item = getItem(position);
        artifactText.setText(item.getNombre());
        artifactText.getBackground().setAlpha(200);

        Glide.with(artifactImage.getContext())
                .load(item.getIdDrawable())
                .into(artifactImage);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Fragment artifactView = new ArtifactInfo(position, true);
                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.free_view, artifactView);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}


