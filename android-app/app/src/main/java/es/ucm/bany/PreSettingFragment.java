package es.ucm.bany;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class PreSettingFragment extends Fragment {

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

        view.findViewById(R.id.button_presseting_theme_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeToTheme(getActivity(), Utils.THEME_DEFAULT);
            }
        });

        view.findViewById(R.id.button_presseting_theme_dark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeToTheme(getActivity(), Utils.THEME_DARK);
            }
        });

        view.findViewById(R.id.button_presseting_theme_pascal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeToTheme(getActivity(), Utils.THEME_PASCAL);
            }
        });

        view.findViewById(R.id.button_presseting_font_max).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.text_presseting_example);
                System.out.println(text);
                //text.setText("HOLA");
                //text.setTextSize(text.getTextSize() + 2);
            }
        });

        view.findViewById(R.id.button_presseting_font_min).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.text_presseting_example);
                text.setText("ADIOS");
                //text.setTextSize(text.getTextSize() - 2);
            }
        });
    }
}