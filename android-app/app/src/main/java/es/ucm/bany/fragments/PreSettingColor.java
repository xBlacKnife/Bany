package es.ucm.bany.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import es.ucm.bany.R;
import es.ucm.bany.Utils;


public class PreSettingColor extends Fragment {

    Button _button_pre_setting_color_next,
            _button_pre_setting_color_pascal,
            _button_pre_setting_color_dark,
            _button_pre_setting_color_light;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_setting_color, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _button_pre_setting_color_next = (Button) view.findViewById(R.id.button_pre_setting_color_next);

        _button_pre_setting_color_pascal = (Button) view.findViewById(R.id.button_pre_setting_color_pascal);
        _button_pre_setting_color_dark = (Button) view.findViewById(R.id.button_pre_setting_color_dark);
        _button_pre_setting_color_light = (Button) view.findViewById(R.id.button_pre_setting_color_light);

        initFragmentElements();
    }


    /**
     * Le asigna el comportamiento a los elementos que hay en el Fragment
     */
    public void initFragmentElements(){

        _button_pre_setting_color_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PreSettingColor.this)
                        .navigate(R.id.action_PreSettingColorFragment_to_PreSettingFontFragment);
            }
        });

        _button_pre_setting_color_pascal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getColorTheme() != Utils.THEME_DEFAULT)
                    Utils.setColorTheme(getActivity(), Utils.THEME_DEFAULT);
            }
        });

        _button_pre_setting_color_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getColorTheme() != Utils.THEME_DARK)
                    Utils.setColorTheme(getActivity(), Utils.THEME_DARK);
            }
        });

        _button_pre_setting_color_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getColorTheme() != Utils.THEME_LIGHT)
                    Utils.setColorTheme(getActivity(), Utils.THEME_LIGHT);
            }
        });

    } // initFragmentElements
}