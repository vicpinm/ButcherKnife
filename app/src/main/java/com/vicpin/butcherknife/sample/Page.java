package com.vicpin.butcherknife.sample;

import com.vicpin.butcherknife.annotation.BindText;

/**
 * Created by Oesia on 07/12/2017.
 */

public class Page {

    @BindText(id = 4)
    public String title;

    public String getTitle() {
        return title;
    }
}
