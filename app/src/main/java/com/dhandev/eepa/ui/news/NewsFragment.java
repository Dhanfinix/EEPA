package com.dhandev.eepa.ui.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dhandev.eepa.NewsWebView;
import com.dhandev.eepa.R;
import com.dhandev.eepa.databinding.FragmentNewsBinding;

import java.net.URI;
import java.util.Locale;

public class NewsFragment extends Fragment {

    private NewsViewModel dashboardViewModel;
    private FragmentNewsBinding binding;
    ImageView nature, scinews, newsci, scitech, phyorg;
    WebView webViewNews;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        nature  = view.findViewById(R.id.nature);
        scinews = view.findViewById(R.id.scinews);
        newsci  = view.findViewById(R.id.newsci);
        scitech = view.findViewById(R.id.scitech);
        phyorg  = view.findViewById(R.id.phyorg);

        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsWebView.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}