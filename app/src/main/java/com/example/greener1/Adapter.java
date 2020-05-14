package com.example.greener1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {
    int[]layouts;
    LayoutInflater layoutInflater;


      public Adapter(Context context, int[] layouts){
          this.layouts=layouts;
          this.layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);



      }

      //return view objects
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(layouts[position],container, false);
        container.addView(view);
        return view;
    }

    //return the size of the array
    @Override
    public int getCount() {
        return layouts.length;
    }


    //compare if our view is equal to object or not - true or false
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    //first layout will be destroyed and the next will be visible
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
          container.removeView((View) object);

    }
}
