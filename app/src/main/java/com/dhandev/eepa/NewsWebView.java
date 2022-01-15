package com.dhandev.eepa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.dhandev.eepa.ui.news.NewsFragment;

public class NewsWebView extends AppCompatActivity {

    ImageView backBTN, homeNewsBTN, forwardBTN;
    WebView webViewNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);
        backBTN     = findViewById(R.id.backBTN);
        homeNewsBTN = findViewById(R.id.homeNewsBTN);
        forwardBTN  = findViewById(R.id.forwardBTN);
        webViewNews = findViewById(R.id.webviewNews);

        webViewNews.loadUrl("https://www.nature.com/subjects/particle-physics");
        webViewNews.setWebViewClient(new WebViewClient());

        forwardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webViewNews.canGoForward()){
                    webViewNews.goForward();
                }
            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webViewNews.canGoBack()){
                    webViewNews.goBack();
                }
            }
        });

        homeNewsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.createNavigateOnClickListener(R.id.navigation_news); //smooth transition
//                Navigation.findNavController(v).navigate(R.id.navigation_news); //bad transition
                finish();
            }
        });
    }
}