package com.example.uvmessenger.view.display;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.uvmessenger.R;
import com.example.uvmessenger.common.common;
import com.example.uvmessenger.databinding.ActivityViewImageBinding;

public class VIewImageActivity extends AppCompatActivity {

    private ActivityViewImageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_view_image);

        binding.imageView.setImageBitmap(common.IMAGE_BITMAP);
    }
}