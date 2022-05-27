package com.example.smd.recyclerviewtest.BL;

import android.content.Context;
import android.util.Log;

import com.example.smd.recyclerviewtest.DB.DB_Interface;
import com.example.smd.recyclerviewtest.DB.Instance;
import com.example.smd.recyclerviewtest.Models.CloudObserver;
import com.example.smd.recyclerviewtest.Models.*;

import java.util.ArrayList;

public class Donors implements BL_Interface{
    static DB_Interface db;
    public Donors(CloudObserver cloudObserver)
    {
        if(db==null)
            db = Instance.getDB(cloudObserver);
    }
    @Override
    public ArrayList<DonorsData> getDonors() {
        ArrayList<DonorsData> donorsData = db.getDonors();
        Log.w("BL", "Recieved values "+donorsData.size());
        return donorsData;
    }

    @Override
    public boolean addDonor(DonorsData donor) {
        return db.addDonor(donor);
    }
}
