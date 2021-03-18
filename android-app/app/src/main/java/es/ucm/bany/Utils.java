package es.ucm.bany;

import android.app.Activity;
import android.content.Intent;
public class Utils
{
    private static int sTheme = 0;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_DARK = 1;
    public final static int THEME_PASCAL = 2;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.Theme_Bany_Light);
                break;
            case THEME_DARK:
                activity.setTheme(R.style.Theme_Bany_Dark);
                break;
            case THEME_PASCAL:
                activity.setTheme(R.style.Theme_Bany_Pascal);
                break;
        }
    }
}
