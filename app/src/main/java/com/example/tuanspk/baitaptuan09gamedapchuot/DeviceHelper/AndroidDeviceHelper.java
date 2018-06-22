package com.example.tuanspk.baitaptuan09gamedapchuot.DeviceHelper;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.tuanspk.baitaptuan09gamedapchuot.MainActivity;

public class AndroidDeviceHelper {
    public static int getWithScreen(MainActivity context){
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    public static int getHeightSreen(MainActivity context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        return size.y;
    }
}
