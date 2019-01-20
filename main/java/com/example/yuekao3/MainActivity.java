package com.example.yuekao3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pag;
    private Button button;
    private RadioGroup rg;
    int arr[] = {R.drawable.a, R.drawable.c, R.drawable.d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        final List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(arr[i]);
            list.add(imageView);
            RadioButton bb = new RadioButton(MainActivity.this);
            rg.addView(bb);

        }
        rg.check(rg.getChildAt(0).getId());
        pag.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView image = list.get(position);
                container.addView(image);
                return image;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        pag.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i == list.size() - 1) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int i) {
                rg.check(rg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initView() {
        pag = (ViewPager) findViewById(R.id.pag);
        button = (Button) findViewById(R.id.button);
        rg = (RadioGroup) findViewById(R.id.rg);

        //button.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });
    }
}


