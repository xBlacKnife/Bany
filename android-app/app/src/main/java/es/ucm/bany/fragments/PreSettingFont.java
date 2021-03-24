package es.ucm.bany.fragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import es.ucm.bany.R;
import es.ucm.bany.Utils;


public class PreSettingFont extends Fragment {

    private static final int FONT_CHANGE_SIZE = 2;

    Button _button_pre_setting_font_next,
            _button_pre_setting_font_inc,
            _button_pre_setting_font_dec;

    TextView _textview_pre_setting_font_example;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_setting_font, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _button_pre_setting_font_next = (Button) view.findViewById(R.id.button_pre_setting_font_next);

        _button_pre_setting_font_inc = (Button) view.findViewById(R.id.button_pre_setting_font_inc);
        _button_pre_setting_font_dec = (Button) view.findViewById(R.id.button_pre_setting_font_dec);

        initFragmentElements();
    }


    /**
     * Le asigna el comportamiento a los elementos que hay en el Fragment
     */
    public void initFragmentElements(){

        _button_pre_setting_font_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PreSettingFont.this)
                        .navigate(R.id.action_PreSettingFontFragment_to_MenuFragment);
            }
        });

        _button_pre_setting_font_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int size = Utils.getFontSizeTheme();
                if(size < Utils.FONT_L){
                    Utils.setFontSizeTheme(getActivity(), size + 1);
                }

            }
        });

        _button_pre_setting_font_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int size = Utils.getFontSizeTheme();
                if(size > Utils.FONT_S){
                    Utils.setFontSizeTheme(getActivity(), size - 1);
                }
            }
        });

    } // initFragmentElements
}