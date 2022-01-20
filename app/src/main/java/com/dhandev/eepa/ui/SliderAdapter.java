package com.dhandev.eepa.ui;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.dhandev.eepa.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;


    }

    //array
    public int [] slide_image ={
            R.drawable.boson,
            R.drawable.fermion,
            R.drawable.model_standar
    };

    public String [] slide_Header={
            "Boson",
            "Fermion",
            "Model Standar"
    };

    public String [] slide_Desc = {
            "Boson merupakan partikel elementer yang mematuhi aturan distribusi Bose-Einstein",
            "Fermion merupakan partikel elementer yang mematuhi aturan distribusi Fermi-Dirac",
            "Model Standar merupakan model kuantum yang menjelaskan tiga gaya fundamental, selain gravitasi"
    };

    @Override
    public int getCount() {
        return slide_Header.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView    = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeaderView    = (TextView) view.findViewById(R.id.slideHeader);
        TextView slideDescView      = (TextView) view.findViewById(R.id.slideDesc);

        slideImageView.setImageResource(slide_image[position]);
        slideHeaderView.setText(slide_Header[position]);
        slideDescView.setText(slide_Desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
