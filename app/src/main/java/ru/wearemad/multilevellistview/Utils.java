package ru.wearemad.multilevellistview;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Artem on 17.12.2016.
 */

public class Utils {

    public Utils () {

    }
    public static int dpToPixels (Context context, int dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
