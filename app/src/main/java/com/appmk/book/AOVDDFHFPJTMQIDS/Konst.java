package com.appmk.book.AOVDDFHFPJTMQIDS;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Askhat on 6/14/2016.
 */

public class Konst {

    public static final String APP_ID = "5D4A660C-7CE3-E3B7-FF95-E8E86BA28E00";
    public static final String ANDROID_KEY = "5F90F275-84EE-D10F-FF29-8D767F5D6400";

    public static void showToast(Context ctx, String message)
    {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }
}
