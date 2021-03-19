package es.ucm.bany;

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

import org.w3c.dom.Text;

public class PreSettingFragment extends Fragment {

    Button _button_presseting_theme_light,
            _button_presseting_theme_dark,
            _button_presseting_theme_pascal,
            _button_presseting_font_max,
            _button_presseting_font_min;

    TextView _text_presseting_example;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presettings, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _button_presseting_theme_light = (Button) view.findViewById(R.id.button_presseting_theme_light);
        _button_presseting_theme_dark = (Button) view.findViewById(R.id.button_presseting_theme_dark);
        _button_presseting_theme_pascal = (Button) view.findViewById(R.id.button_presseting_theme_pascal);

        _button_presseting_font_max = (Button) view.findViewById(R.id.button_presseting_font_max);
        _button_presseting_font_min = (Button) view.findViewById(R.id.button_presseting_font_min);
        _text_presseting_example = (TextView) view.findViewById(R.id.text_presseting_example);


        _button_presseting_theme_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeToTheme(getActivity(), Utils.THEME_DEFAULT);
            }
        });

        _button_presseting_theme_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeToTheme(getActivity(), Utils.THEME_DARK);
            }
        });

        _button_presseting_theme_pascal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeToTheme(getActivity(), Utils.THEME_PASCAL);
            }
        });

        _button_presseting_font_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _text_presseting_example.setTextSize(TypedValue.COMPLEX_UNIT_PX, _text_presseting_example.getTextSize() + 8);
            }
        });

        _button_presseting_font_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _text_presseting_example.setTextSize(TypedValue.COMPLEX_UNIT_PX, _text_presseting_example.getTextSize() - 8);
            }
        });
    }
}