package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.SplashScreenBinding;

public class SplashscreenActivity extends AppCompatActivity {

    private SplashScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView leaf = binding.leaf;
        TextView welcome = binding.welcome;
        TextView recycleYourWasteMaterials = binding.recycleYourWasteMaterials;
        ImageView can = binding.can;
        ImageView vector1 = binding.vector1;
        ImageView vector2 = binding.vector2;
        ImageView vector3 = binding.vector3;
        ImageView leaf1 = binding.leaf1;
        ImageView news = binding.news;
        ImageView dustbin = binding.dustbin;

        new Handler().postDelayed(() -> {
            // Start your main activity or login activity based on your logic
            startActivity(new Intent(SplashscreenActivity.this, LoginActivity.class));
            finish();
        }, 3000L);
    }
}
