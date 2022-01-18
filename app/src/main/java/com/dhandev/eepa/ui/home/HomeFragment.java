package com.dhandev.eepa.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dhandev.eepa.Materi;
import com.dhandev.eepa.R;
import com.dhandev.eepa.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.squareup.picasso.Picasso;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ImageCarousel carousel;
    private Button test, update;
    private ImageView imageView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SharedPreferences getSharedPreferences;


    FirebaseRemoteConfig firebaseRemoteConfig;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false); //imageview helper
        carousel = view.findViewById(R.id.carousel);
        test    = view.findViewById(R.id.test);
        update  = view.findViewById(R.id.update);
        imageView  =view.findViewById(R.id.gambar);
        swipeRefreshLayout  = view.findViewById(R.id.swipeRefresh);

        //init
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().build();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        //init default value for firebase remote config
        Map<String, Object> defaultData = new HashMap<>();
        defaultData.put("test","version 1.0");
        defaultData.put("update", false);
        defaultData.put("image_link","https://images.pexels.com/photos/2916826/pexels-photo-2916826.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        firebaseRemoteConfig.setDefaultsAsync(defaultData);

        //load image
        Picasso.get().load("https://images.pexels.com/photos/2916826/pexels-photo-2916826.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .into(imageView);

        //event pengambilan data baru dari firebase console
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                firebaseRemoteConfig.fetch(0) //time to live of cache, if 0 it will refresh without cache
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    firebaseRemoteConfig.activate();
                                    test.setText(firebaseRemoteConfig.getString("test"));
                                    test.setEnabled(firebaseRemoteConfig.getBoolean("update"));
                                    Picasso.get().load(firebaseRemoteConfig.getString("image_link")).into(imageView);
                                    Toast.makeText(getActivity(),"Updated",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getActivity(),"No Update"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        FirebaseDatabase.getInstance().setPersistenceEnabled(true); //how to make data updated permanent

        //sama aja kyk diatas, cuma pakai button
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseRemoteConfig.fetch(0) //time to live of cache, if 0 it will refresh without cache
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful())
//                            {
//                                firebaseRemoteConfig.activate();
//                                test.setText(firebaseRemoteConfig.getString("test"));
//                                test.setEnabled(firebaseRemoteConfig.getBoolean("update"));
//                                Picasso.get().load(firebaseRemoteConfig.getString("image_link")).into(imageView);
//                            }
//                            else {
//                                Toast.makeText(getActivity(),""+task.getException().getMessage(),Toast.LENGTH_LONG);
//                            }
//                        }
//                    });
//            }
//        });


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
      //  View root = binding.getRoot();

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