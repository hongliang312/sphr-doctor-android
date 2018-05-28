package com.lightheart.sphr.doctor.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lightheart.sphr.doctor.R;
import com.youth.banner.loader.ImageLoader;

/**
 * 图片加载工具类
 */
public class ImageLoaderUtils extends ImageLoader {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(placeholder).error(error).centerCrop())
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, int drawable, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions().placeholder(placeholder).error(error).centerCrop())
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_image_loading).error(R.mipmap.ic_image_loadfail).centerCrop())
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, int drawableId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(drawableId)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_image_loading).error(R.mipmap.ic_image_loadfail).centerCrop())
                .into(imageView);
    }


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_image_loading).diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(R.mipmap.ic_image_loadfail))
                .into(imageView);
    }

}
