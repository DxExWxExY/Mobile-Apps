package com.dxexwxexy.labs.Fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dxexwxexy.labs.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("pattern");

        PatternDetailFragment fragment = (PatternDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        fragment.setPattern(name);
    }

}
