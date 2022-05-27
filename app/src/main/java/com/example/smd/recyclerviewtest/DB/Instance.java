package com.example.smd.recyclerviewtest.DB;

import android.content.Context;

import com.example.smd.recyclerviewtest.Models.CloudObserver;

import java.util.ConcurrentModificationException;

public class Instance {
    //    public static DB_Interface getDB(Context context){
//        return new DatabaseHelper(context);
//    }
    public static DB_Interface getDB(CloudObserver cloudObserver) {
        return new DatabaseCloud(cloudObserver);
    }
}
