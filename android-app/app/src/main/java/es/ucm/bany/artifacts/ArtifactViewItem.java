package es.ucm.bany.artifacts;

import es.ucm.bany.R;

public class ArtifactViewItem {

    private String name;
    private int image;

    private ArtifactViewItem(String nombre, int idDrawable) {
        this.name = nombre;
        this.image = idDrawable;
    }

    public String getNombre() {
        return name;
    }

    public int getIdDrawable() {
        return image;
    }

    public int getId() {
        return name.hashCode();
    }

    public static ArtifactViewItem[] ITEMS = {
            new ArtifactViewItem("sample", R.drawable.yoshi_wallpaper),
            new ArtifactViewItem("sample2", R.drawable.ganondorf),
            new ArtifactViewItem("sample3", R.drawable.sauron),
            new ArtifactViewItem("sample4", R.drawable.kiss),
    };

    public static ArtifactViewItem getItem(int id) {
        for (ArtifactViewItem item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
