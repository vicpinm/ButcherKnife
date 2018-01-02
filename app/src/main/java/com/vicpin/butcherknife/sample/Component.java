package com.vicpin.butcherknife.sample;

import android.content.Context;

import javax.inject.Singleton;



/**
 * Created by victor on 10/12/17.
 */

@Singleton
@dagger.Component(modules = MyModule.class)
public interface Component {

    Context getContext();

}
