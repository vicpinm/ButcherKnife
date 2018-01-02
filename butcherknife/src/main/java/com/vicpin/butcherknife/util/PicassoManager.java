package com.vicpin.butcherknife.util;

import android.content.Context;

import com.squareup.picasso.Picasso;


public class PicassoManager {

    private static Picasso instance = null;

    public static Picasso get(Context context) {
        if(instance == null) {
            instance = new Picasso.Builder(context).build();
        }
        return instance;
    }

    public static void pause() {
        if(instance != null) {
            instance.pauseTag(instance);
        }
    }

    public static void resume() {
        if(instance != null) {
            instance.resumeTag(instance);
        }
    }


}
