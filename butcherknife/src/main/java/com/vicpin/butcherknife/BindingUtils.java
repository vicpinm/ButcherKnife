package com.vicpin.butcherknife;

import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.RequestCreator;
import com.vicpin.butcherknife.annotation.CornerType;
import com.vicpin.butcherknife.util.PicassoCornersTransformation;
import com.vicpin.butcherknife.util.PicassoManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import events.symposium.app.util.CircleTransform;



public class BindingUtils {

    public static String formatDate(long timestamp, String format) {
        return formatDate(new Date(timestamp), format);
    }

    public static String formatDate(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }


    public static void loadWithPicasso(ImageView img, String imageUrl, int placeholder, boolean cropped, boolean isFile, boolean isCircle,
                                       CornerType cornerType, int cornerRadius, int cornerMargin) {

        if(TextUtils.isEmpty(imageUrl) && placeholder == 0){
            return;
        }

        RequestCreator creator;

        if (!TextUtils.isEmpty(imageUrl)) {
            if(isFile && new File(imageUrl).exists()) {
                creator = PicassoManager.get(img.getContext()).load(new File(imageUrl));
            }
            else {
                creator = PicassoManager.get(img.getContext()).load(imageUrl);
            }

            if(placeholder > 0) {
                creator.placeholder(placeholder);
            }

        } else {
            creator = PicassoManager.get(img.getContext()).load(placeholder);
        }

        if(cropped) {
            creator.fit().centerCrop();
        }

        if(isCircle) {
            creator.transform(new CircleTransform());
        }
        else if(cornerType != null && cornerRadius > 0){
            int cornerMarginPx = 0;
            if(cornerMargin > 0) {
                cornerMarginPx = img.getResources().getDimensionPixelSize(cornerMargin);
            }
            creator.transform(new PicassoCornersTransformation(img.getResources().getDimensionPixelSize(cornerRadius), cornerMarginPx, cornerType));

        }
        creator.into(img);
    }



}
