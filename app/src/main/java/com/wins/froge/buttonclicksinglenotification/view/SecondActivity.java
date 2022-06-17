package com.wins.froge.buttonclicksinglenotification.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wins.froge.buttonclicksinglenotification.databinding.ActivitySecondBinding;

import java.util.ConcurrentModificationException;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = getApplicationContext();

        initUi();
        initClickListener();
    }


    private void initUi() {
        Intent intent = getIntent();
        if(intent.hasExtra("object_id"))
        {
            String data = intent.getStringExtra("object_id");
            Log.d("TAG", data);


        }

    }


    private void initClickListener() {

    }
}