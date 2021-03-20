package es.ucm.bany;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils
{
    private static int _theme;

    private static int _color;
    private static int _fontSize;

    public final static int THEME_DEFAULT = 0;
    public final static int THEME_DARK = 1;
    public final static int THEME_LIGHT = 2;

    public final static int FONT_S = 0;
    public final static int FONT_M = 1;
    public final static int FONT_L = 2;

    private static HashMap<Integer, List<Integer>> _themesDict = new HashMap<Integer, List<Integer>>(){{
            put(THEME_DEFAULT, Arrays.asList(R.style.Theme_Bany_Pascal_S,
                    R.style.Theme_Bany_Pascal_M, R.style.Theme_Bany_Pascal_L)
            );
            put(THEME_DARK, Arrays.asList(R.style.Theme_Bany_Dark_S,
                    R.style.Theme_Bany_Dark_M, R.style.Theme_Bany_Dark_L)
            );
            put(THEME_LIGHT, Arrays.asList(R.style.Theme_Bany_Light_S,
                    R.style.Theme_Bany_Light_M, R.style.Theme_Bany_Light_L)
            );
    }};

    public static void setColorTheme(Activity activity, int color){
        _color = color;
        _theme = ((List<Integer>)_themesDict.get(_color)).get(_fontSize);
        changeToTheme(activity);
    }

    public static int getColorTheme(){
        return _color;
    }

    public static void setFontSizeTheme(Activity activity, int size){
        _fontSize = size;
        _theme = ((List<Integer>)_themesDict.get(_color)).get(_fontSize);
        changeToTheme(activity);
    }

    public static int getFontSizeTheme(){
        return _fontSize;
    }

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    private static void changeToTheme(Activity activity) {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        activity.setTheme(_theme);

        /*switch (sTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.Theme_Bany_Pascal_S);
                break;
            case THEME_DARK_S:
                activity.setTheme(R.style.Theme_Bany_Dark_S);
                break;
            case THEME_LIGHT_S:
                activity.setTheme(R.style.Theme_Bany_Light_S);
                break;

            case THEME_DEFAULT_M:
                activity.setTheme(R.style.Theme_Bany_Pascal_M);
                break;
            case THEME_DARK_M:
                activity.setTheme(R.style.Theme_Bany_Dark_M);
                break;
            case THEME_LIGHT_M:
                activity.setTheme(R.style.Theme_Bany_Light_M);
                break;

            case THEME_DEFAULT_L:
                activity.setTheme(R.style.Theme_Bany_Pascal_L);
                break;
            case THEME_DARK_L:
                activity.setTheme(R.style.Theme_Bany_Dark_L);
                break;
            case THEME_LIGHT_L:
                activity.setTheme(R.style.Theme_Bany_Light_L);
                break;
        }*/
    }
}
