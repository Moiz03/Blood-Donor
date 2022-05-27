package com.example.smd.recyclerviewtest.DB;

import com.example.smd.recyclerviewtest.Models.*;

import java.util.ArrayList;

public interface DB_Interface {
    public boolean addDonor(DonorsData donor);
    public ArrayList<DonorsData> getDonors();
}
