package com.dhandev.eepa.ui.settings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dhandev.eepa.R;
import com.dhandev.eepa.databinding.FragmentSettingsBinding;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    private SettingsViewModel SettingsViewModel;
    private FragmentSettingsBinding binding;
    String selectedLang = "English";
    Button bahasa, logout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        loadLocale();


        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bahasa = root.findViewById(R.id.UbahBahasa);
        logout = root.findViewById(R.id.logout);

        //buat tombol logout!
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialogue();
            }
        });

        return root;
    }

    private void showChangeLangDialogue() {
        final String[] listItems = {"English","Bahasa Indonesia"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setTitle("Please select language");
        mBuilder.setSingleChoiceItems(listItems, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedLang = listItems[which];
                if (which==0){
                    setLocale("en");
                }
                else if (which==1){
                    setLocale("in");
                }
            }
        });
        mBuilder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().recreate();
                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.show();
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getContext().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}