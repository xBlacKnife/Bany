package es.ucm.bany.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import es.ucm.bany.R;

public class CarAdapter extends BaseAdapter {
    private Context context;

    public CarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Coche.ITEMS.length;
    }

    @Override
    public Coche getItem(int position) {
        return Coche.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen_coche);
        TextView nombreCoche = (TextView) view.findViewById(R.id.nombre_coche);

        final Coche item = getItem(position);
        imagenCoche.setImageResource(item.getIdDrawable());
        nombreCoche.setText(item.getNombre());

        return view;
    }

}
