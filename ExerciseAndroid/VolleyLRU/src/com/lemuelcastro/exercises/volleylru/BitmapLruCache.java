package com.lemuelcastro.exercises.volleylru;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

//suppress for byte count but still lower versions are checkd using 
//a ternary in method sizeOf
@SuppressLint("NewApi")
public class BitmapLruCache extends LruCache<String , Bitmap> implements ImageCache{
	
	 public static int getDefaultLruCacheSize() {
	        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	        final int cacheSize = maxMemory / 8;

	        return cacheSize;
	    }

	    public BitmapLruCache() {
	        this(getDefaultLruCacheSize());
	    }

	    public BitmapLruCache(int sizeInKiloBytes) {
	        super(sizeInKiloBytes);
	    }

	    @Override
	    protected int sizeOf(String key, Bitmap value) {
	        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)? value.getByteCount(): value.getRowBytes() * value.getHeight();
	    }

	    @Override
	    public Bitmap getBitmap(String url) {
	        return get(url);
	    }

	    @Override
	    public void putBitmap(String url, Bitmap bitmap) {
	        put(url, bitmap);
	    }
}


