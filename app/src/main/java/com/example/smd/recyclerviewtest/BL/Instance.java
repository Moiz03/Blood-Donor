package com.example.smd.recyclerviewtest.BL;

import android.content.Context;

import com.example.smd.recyclerviewtest.Models.CloudObserver;


public class Instance {
//    public static BL_Interface getInstance(Context context)
//    {
//        return new Donors(context);
//    }

    public static BL_Interface getInstance(CloudObserver cloudObserver)
    {
        return new Donors(cloudObserver);
    }
}
