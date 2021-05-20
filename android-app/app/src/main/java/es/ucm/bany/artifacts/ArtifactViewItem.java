package es.ucm.bany.artifacts;

import es.ucm.bany.R;

public class ArtifactViewItem {

    private String nameID;
    private String name;
    private int image;

    private ArtifactViewItem(String nombre, String nombreID, int idDrawable) {
        this.name = nombre;
        this.nameID = nombreID;
        this.image = idDrawable;
    }

    public String getNombre() {
        return name;
    }

    public String getNameID() {
        return nameID;
    }

    public int getIdDrawable() {
        return image;
    }

    public int getId() {
        return name.hashCode();
    }

    public static ArtifactViewItem[] ITEMS = {
            new ArtifactViewItem("Sensory Chess Challenger 8", "ajedrez", R.drawable.ajedrez),
            new ArtifactViewItem("Amstrad Sinclair ZX Spectrum", "amstrad", R.drawable.amstrad),
            new ArtifactViewItem("Atari 2600", "atari", R.drawable.atari),
            new ArtifactViewItem("Historia de los discos duros", "discosduros", R.drawable.discosduros),
            new ArtifactViewItem("Macintosh SE", "macintoshse", R.drawable.macintoshse),
            new ArtifactViewItem("Obleas de silicio", "obleas", R.drawable.obleas),
            new ArtifactViewItem("Edad de oro de los videojuegos en España", "paneljuegos", R.drawable.paneljuegos),
            new ArtifactViewItem("Revistas videojuegos años 80", "panelrevistas", R.drawable.panelrevistas),
            new ArtifactViewItem("SEGA Mega Drive", "segamega", R.drawable.segamega),
            new ArtifactViewItem("Analizador diferencial", "analizadordiff", R.drawable.analizadordiff),
            new ArtifactViewItem("Refrigeración líquida", "refrigeración", R.drawable.refrigeracion),
            new ArtifactViewItem("Recreativa", "recreativa", R.drawable.recreativa),
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
