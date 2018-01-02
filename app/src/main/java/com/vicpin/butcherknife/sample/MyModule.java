package com.vicpin.butcherknife.sample;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by victor on 10/12/17.
 */

@Module
public class MyModule {

    Context context;

    public MyModule(Context c){
        context = c;
    }

    @Singleton @Provides public Context getContext(){
        return context;
    }
}
