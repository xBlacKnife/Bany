package es.ucm.bany.artifacts;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;

import es.ucm.bany.MainActivity;
import es.ucm.bany.R;
import es.ucm.bany.fragments.ArtifactInfo;

public class ArtifactAdapter extends BaseAdapter {
    private Context context;

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
        }
        final ArtifactViewItem item = getItem(position);

        ImageView artifactImage = (ImageView) view.findViewById(R.id.imagen_coche);
        TextView artifactText = (TextView) view.findViewById(R.id.nombre_coche);
        artifactImage.setImageResource(item.getIdDrawable());
        artifactText.setText(item.getNombre());
        artifactText.setTextColor(Color.WHITE);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Fragment artifactView = new ArtifactInfo(item);
                FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.free_view, artifactView);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}
