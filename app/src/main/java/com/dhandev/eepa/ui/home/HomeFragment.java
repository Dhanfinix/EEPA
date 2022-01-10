package com.dhandev.eepa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dhandev.eepa.Materi;
import com.dhandev.eepa.R;
import com.dhandev.eepa.databinding.FragmentHomeBinding;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ImageCarousel carousel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false); //imageview helper
        carousel = view.findViewById(R.id.carousel);

        //gambar pada carousel
        List<CarouselItem> listCar=new ArrayList<>();

        listCar.add(
                new CarouselItem(
                        R.drawable.materiscroll1
                )
        );
        listCar.add(
                new CarouselItem(
                        R.drawable.materiscroll2
                )
        );
        listCar.add(
                new CarouselItem(
                        R.drawable.materiscroll3
                )
        );

        carousel.addData(listCar);

        carousel.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onLongClick(int i, CarouselItem carouselItem) {
                if (i==0){
                    teori();
                } else if (i==1){
                    teori();
                } else if (i==2){
                    teori();
                }

            }

            @Override
            public void onClick(int i, CarouselItem carouselItem) {
                if (i == 0) {
                    teori();
                } else if (i == 1) {
                    teori();
                } else if (i==2){
                    teori();
                }
            }
        });


//        teori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Materi.class);
//                startActivity(intent);
//            }
//        });
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return view;  //perhatikan return kudu view, agar bisa pindah activity, jika root tidak pindah
    }
    public void teori(){
        Intent intent =new Intent(getActivity(), Materi.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}